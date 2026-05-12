package com.myapp.buckwheat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.buckwheat.data.local.entity.TransactionEntity
import com.myapp.buckwheat.data.repository.PeriodRepository
import com.myapp.buckwheat.data.repository.SettingsRepository
import com.myapp.buckwheat.data.repository.TransactionRepository
import com.myapp.buckwheat.data.local.entity.PeriodEntity
import com.myapp.buckwheat.domain.model.Transaction
import com.myapp.buckwheat.domain.model.toDomain
import com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase
import com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

data class MainUiState(
    val isLoading: Boolean = true,
    val onboardingDone: Boolean = false,
    val isPeriodSet: Boolean = false,
    val periodStart: LocalDate? = null,
    val periodEnd: LocalDate? = null,
    val currencySymbol: String = "TK",
    val currencyCode: String = "BDT",
    val currentBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalSpent: Double = 0.0,
    val dailyBudget: Double = 0.0,
    val todaySpent: Double = 0.0,
    val daysLeft: Int = 0,
    val averageSpend: Double = 0.0,
    val todayTransactions: List<Transaction> = emptyList(),
    val allPeriodTransactions: List<Transaction> = emptyList(),
    val isPeriodEnded: Boolean = false,
    val showResidueDialog: Boolean = false,
    val residueSurplus: Double = 0.0,
    val isEditingPeriod: Boolean = false,
    val themeMode: String = "system",
    val hideOverspendWarn: Boolean = false,
    val residueMethod: String = "ask",
    val showWalletSheet: Boolean = false,
    val showIncomeSheet: Boolean = false,
    val showAdjustDialog: Boolean = false,
    val inputAmount: String = "0",
    val inputTag: String = "",
    val distinctTags: List<String> = emptyList(),
    val lastIncome: TransactionEntity? = null,
    val showDevilFruitUnlock: Boolean = false
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val settingsRepository: SettingsRepository,
    private val periodRepository: PeriodRepository,
    private val calculateDailyBudgetUseCase: CalculateDailyBudgetUseCase,
    private val calculateBalanceUseCase: CalculateBalanceUseCase,
    val formatAmountUseCase: FormatAmountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _sanjiEvent = Channel<Unit>(Channel.BUFFERED)
    val sanjiEvent = _sanjiEvent.receiveAsFlow()

    init {
        observeSettings()
        observeTags()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            combine(
                settingsRepository.periodStart,
                settingsRepository.periodEnd,
                settingsRepository.currencySymbol,
                settingsRepository.currencyCode,
                settingsRepository.onboardingDone,
                settingsRepository.themeMode,
                settingsRepository.hideOverspend,
                settingsRepository.residueMethod
            ) { values ->
                val periodStartStr = values[0] as String
                val periodEndStr = values[1] as String
                val symbol = values[2] as String
                val code = values[3] as String
                val onboarding = values[4] as Boolean
                val theme = values[5] as String
                val hideOverspend = values[6] as Boolean
                val residue = values[7] as String

                SettingsSnapshot(
                    periodStartStr, periodEndStr, symbol, code,
                    onboarding, theme, hideOverspend, residue
                )
            }.collect { snapshot ->
                val periodStart = if (snapshot.periodStart.isNotEmpty())
                    LocalDate.parse(snapshot.periodStart) else null
                val periodEnd = if (snapshot.periodEnd.isNotEmpty())
                    LocalDate.parse(snapshot.periodEnd) else null
                val isPeriodSet = periodStart != null && periodEnd != null
                val today = LocalDate.now()

                val isPeriodEnded = if (periodEnd != null) today.isAfter(periodEnd) else false

                _uiState.update {
                    it.copy(
                        onboardingDone = snapshot.onboardingDone,
                        periodStart = periodStart,
                        periodEnd = periodEnd,
                        isPeriodSet = isPeriodSet,
                        currencySymbol = snapshot.symbol,
                        currencyCode = snapshot.code,
                        themeMode = snapshot.theme,
                        hideOverspendWarn = snapshot.hideOverspend,
                        residueMethod = snapshot.residue,
                        isPeriodEnded = isPeriodEnded,
                        isLoading = false
                    )
                }

                if (isPeriodSet && !isPeriodEnded) {
                    observeTransactions(periodStart!!, periodEnd!!, snapshot.symbol)
                }
            }
        }
    }

    private fun observeTransactions(start: LocalDate, end: LocalDate, symbol: String) {
        viewModelScope.launch {
            val today = LocalDate.now()
            val todayStr = today.toString()

            transactionRepository.getTransactionsBetween(start.toString(), end.toString())
                .combine(transactionRepository.getTransactionsOnDate(todayStr)) { allTxns, todayTxns ->
                    Pair(allTxns, todayTxns)
                }
                .collect { (allTxns, todayTxns) ->
                    val totalIncome = allTxns.filter { it.type == "income" }.sumOf { it.amount }
                    val totalSpent = allTxns.filter { it.type == "expense" }.sumOf { it.amount }
                    val totalSubtracted = allTxns.filter { it.type == "adjustment" }.sumOf { it.amount }
                    val totalAdded = allTxns.filter { it.type == "adjustment_add" }.sumOf { it.amount }
                    val balance = totalIncome + totalAdded - totalSpent - totalSubtracted
                    val todayExpenses = todayTxns.filter { it.type == "expense" }.sumOf { it.amount }

                    val daysLeft = ChronoUnit.DAYS.between(today, end).toInt() + 1
                    val dailyBudget = calculateDailyBudgetUseCase(balance, end, today)

                    val daysPassed = ChronoUnit.DAYS.between(start, today).toInt() + 1
                    val avgSpend = if (daysPassed > 0) totalSpent / daysPassed else 0.0

                    _uiState.update {
                        it.copy(
                            totalIncome = totalIncome,
                            totalSpent = totalSpent,
                            currentBalance = balance,
                            todaySpent = todayExpenses,
                            daysLeft = maxOf(daysLeft, 0),
                            dailyBudget = dailyBudget,
                            averageSpend = avgSpend,
                            todayTransactions = todayTxns.map { t -> t.toDomain(symbol) },
                            allPeriodTransactions = allTxns.map { t -> t.toDomain(symbol) }
                        )
                    }
                }
        }
    }

    private fun observeTags() {
        viewModelScope.launch {
            transactionRepository.getDistinctTags().collect { tags ->
                _uiState.update { it.copy(distinctTags = tags) }
            }
        }
    }

    // Input handling
    fun onNumberInput(digit: String) {
        _uiState.update { state ->
            val current = state.inputAmount
            val newAmount = when {
                current == "0" && digit != "." -> digit
                digit == "." && current.contains(".") -> current
                current.contains(".") && current.substringAfter(".").length >= 2 -> current
                else -> current + digit
            }
            state.copy(inputAmount = newAmount)
        }
    }

    fun onBackspace() {
        _uiState.update { state ->
            val current = state.inputAmount
            val newAmount = when {
                current.length <= 1 -> "0"
                else -> current.dropLast(1)
            }
            state.copy(inputAmount = newAmount)
        }
    }

    fun onClear() {
        _uiState.update { it.copy(inputAmount = "0", inputTag = "") }
    }

    fun onTagChanged(tag: String) {
        _uiState.update { it.copy(inputTag = tag) }
    }

    fun addExpense() {
        val state = _uiState.value
        val amount = state.inputAmount.toDoubleOrNull() ?: return
        if (amount <= 0) return

        viewModelScope.launch {
            val now = LocalDateTime.now()
            val entity = TransactionEntity(
                type = "expense",
                amount = amount,
                currency = state.currencySymbol,
                tag = state.inputTag.takeIf { it.isNotBlank() },
                note = null,
                date = now.toLocalDate().toString(),
                time = now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                createdAt = System.currentTimeMillis()
            )
            val countBefore = transactionRepository.getTransactionCount()
            transactionRepository.insert(entity)
            if (countBefore == 0L) {
                _uiState.update { it.copy(showDevilFruitUnlock = true) }
            }
            _uiState.update { it.copy(inputAmount = "0", inputTag = "") }
        }
    }

    fun addBudgetAdjustment(amount: Double, isAddition: Boolean, note: String?) {
        if (amount <= 0) return
        viewModelScope.launch {
            val now = LocalDateTime.now()
            val entity = TransactionEntity(
                type = if (isAddition) "adjustment_add" else "adjustment",
                amount = amount,
                currency = _uiState.value.currencySymbol,
                tag = if (isAddition) "Budget Added \u2795" else "Budget Adjusted \u2796",
                note = note,
                date = now.toLocalDate().toString(),
                time = now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                createdAt = System.currentTimeMillis()
            )
            val countBefore = transactionRepository.getTransactionCount()
            transactionRepository.insert(entity)
            if (countBefore == 0L) {
                _uiState.update { it.copy(showDevilFruitUnlock = true) }
            }
            _uiState.update { it.copy(showAdjustDialog = false) }
        }
    }

    fun toggleAdjustDialog() {
        _uiState.update { it.copy(showAdjustDialog = !it.showAdjustDialog) }
    }

    fun addIncome(
        amount: Double,
        source: String,
        date: LocalDate,
        note: String?
    ) {
        viewModelScope.launch {
            val entity = TransactionEntity(
                type = "income",
                amount = amount,
                currency = _uiState.value.currencySymbol,
                tag = source,
                note = note,
                date = date.toString(),
                time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                createdAt = System.currentTimeMillis()
            )
            val countBefore = transactionRepository.getTransactionCount()
            transactionRepository.insert(entity)
            if (countBefore == 0L) {
                _uiState.update { it.copy(showDevilFruitUnlock = true) }
            }
            
            _uiState.update { it.copy(showIncomeSheet = false) }
            _sanjiEvent.trySend(Unit)
        }
    }



    fun dismissDevilFruitUnlock() {
        _uiState.update { it.copy(showDevilFruitUnlock = false) }
    }

    fun deleteTransaction(id: Long) {
        viewModelScope.launch {
            transactionRepository.deleteById(id)
        }
    }

    // Period management
    fun updatePeriod(start: LocalDate, end: LocalDate) {
        viewModelScope.launch {
            settingsRepository.setPeriodStart(start.toString())
            settingsRepository.setPeriodEnd(end.toString())
            _uiState.update { it.copy(isEditingPeriod = false) }
        }
    }

    fun toggleEditPeriod() {
        _uiState.update { it.copy(isEditingPeriod = !it.isEditingPeriod) }
    }

    fun toggleWalletSheet() {
        _uiState.update { it.copy(showWalletSheet = !it.showWalletSheet) }
    }

    fun toggleIncomeSheet(show: Boolean) {
        viewModelScope.launch {
            if (show) {
                val lastIncome = transactionRepository.getLastIncome()
                _uiState.update { it.copy(showIncomeSheet = true, lastIncome = lastIncome) }
            } else {
                _uiState.update { it.copy(showIncomeSheet = false) }
            }
        }
    }

    fun dismissResidueDialog() {
        _uiState.update { it.copy(showResidueDialog = false) }
    }

    fun handleResidue(addToToday: Boolean, remember: Boolean) {
        viewModelScope.launch {
            if (remember) {
                val method = if (addToToday) "add_today" else "distribute"
                settingsRepository.setResidueMethod(method)
                settingsRepository.setRememberResidue(true)
            }
            _uiState.update { it.copy(showResidueDialog = false) }
        }
    }

    fun finishPeriodEarly() {
        viewModelScope.launch {
            val state = _uiState.value
            if (state.periodStart != null && state.periodEnd != null) {
                val periodEntity = PeriodEntity(
                    startDate = state.periodStart.toString(),
                    endDate = LocalDate.now().toString(),
                    totalIncome = state.totalIncome,
                    totalSpent = state.totalSpent,
                    currency = state.currencySymbol,
                    createdAt = System.currentTimeMillis()
                )
                periodRepository.insert(periodEntity)
                settingsRepository.setPeriodEnd(LocalDate.now().minusDays(1).toString())
            }
        }
    }

    fun checkDayChange() {
        viewModelScope.launch {
            val today = LocalDate.now()
            val lastKnownStr = settingsRepository.getPeriodStartSync()
            if (lastKnownStr.isEmpty()) return@launch

            var lastDateStr = ""
            settingsRepository.lastKnownDate.first().let { lastDateStr = it }

            if (lastDateStr.isEmpty()) {
                settingsRepository.setLastKnownDate(today.toString())
                return@launch
            }

            val lastDate = LocalDate.parse(lastDateStr)
            if (lastDate.isBefore(today)) {
                val state = _uiState.value
                if (state.periodEnd != null) {
                    val yesterdayExpenses = transactionRepository.getTotalExpensesOnDate(lastDate.toString())
                    val yesterdayBudget = calculateDailyBudgetUseCase(
                        state.currentBalance + yesterdayExpenses,
                        state.periodEnd,
                        lastDate
                    )
                    val surplus = yesterdayBudget - yesterdayExpenses

                    if (surplus > 0 && state.residueMethod == "ask") {
                        _uiState.update {
                            it.copy(
                                showResidueDialog = true,
                                residueSurplus = surplus
                            )
                        }
                    }
                }
                settingsRepository.setLastKnownDate(today.toString())
            }
        }
    }

    private data class SettingsSnapshot(
        val periodStart: String,
        val periodEnd: String,
        val symbol: String,
        val code: String,
        val onboardingDone: Boolean,
        val theme: String,
        val hideOverspend: Boolean,
        val residue: String
    )
}
