package com.myapp.buckwheat.ui.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.buckwheat.data.repository.SettingsRepository
import com.myapp.buckwheat.data.repository.TransactionRepository
import com.myapp.buckwheat.domain.model.AnalyticsData
import com.myapp.buckwheat.domain.model.SpendRecord
import com.myapp.buckwheat.domain.model.toDomain
import com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

data class AnalyticsUiState(
    val selectedPeriod: String = "current",  // "current", "30days", "alltime"
    val analyticsData: AnalyticsData = AnalyticsData(),
    val currencySymbol: String = "TK",
    val periodStart: LocalDate? = null,
    val periodEnd: LocalDate? = null,
    val isLoading: Boolean = true,
    val totalTransactionsAllTime: Int = 0
)

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val settingsRepository: SettingsRepository,
    private val calculateDailyBudgetUseCase: CalculateDailyBudgetUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalyticsUiState())
    val uiState: StateFlow<AnalyticsUiState> = _uiState.asStateFlow()

    init {
        loadAnalytics()
    }

    private fun loadAnalytics() {
        viewModelScope.launch {
            val count = transactionRepository.getAllTransactions().first().size
            _uiState.update { it.copy(totalTransactionsAllTime = count) }

            combine(
                settingsRepository.periodStart,
                settingsRepository.periodEnd,
                settingsRepository.currencySymbol
            ) { start, end, symbol ->
                Triple(start, end, symbol)
            }.collect { (startStr, endStr, symbol) ->
                _uiState.update { it.copy(currencySymbol = symbol) }

                if (startStr.isNotEmpty() && endStr.isNotEmpty()) {
                    val start = LocalDate.parse(startStr)
                    val end = LocalDate.parse(endStr)
                    _uiState.update { it.copy(periodStart = start, periodEnd = end) }
                    computeAnalytics(start, end, symbol)
                }
            }
        }
    }

    fun selectPeriod(period: String) {
        _uiState.update { it.copy(selectedPeriod = period, isLoading = true) }
        viewModelScope.launch {
            val state = _uiState.value
            val (start, end) = when (period) {
                "30days" -> Pair(LocalDate.now().minusDays(30), LocalDate.now())
                "alltime" -> Pair(
                    state.periodStart ?: LocalDate.now().minusYears(1),
                    LocalDate.now()
                )
                else -> Pair(
                    state.periodStart ?: LocalDate.now(),
                    state.periodEnd ?: LocalDate.now()
                )
            }
            computeAnalytics(start, end, state.currencySymbol)
        }
    }

    private suspend fun computeAnalytics(start: LocalDate, end: LocalDate, symbol: String) {
        val transactions = transactionRepository.getTransactionsBetweenSync(
            start.toString(), end.toString()
        )

        val expenses = transactions.filter { it.type == "expense" }
        val today = LocalDate.now()
        val daysPassed = ChronoUnit.DAYS.between(start, minOf(today, end)).toInt() + 1

        val totalSpent = expenses.sumOf { it.amount }
        val avgPerDay = if (daysPassed > 0) totalSpent / daysPassed else 0.0

        // Daily spending map
        val dailySpending = expenses.groupBy { it.date }
            .mapKeys { LocalDate.parse(it.key) }
            .mapValues { (_, txns) -> txns.sumOf { it.amount } }

        // Max/min spend
        val maxEntry = dailySpending.maxByOrNull { it.value }
        val minEntry = dailySpending.filter { it.value > 0 }.minByOrNull { it.value }

        // Tag breakdown
        val tagBreakdown = expenses.groupBy { it.tag ?: "Untagged" }
            .mapValues { (_, txns) -> txns.sumOf { it.amount } }

        // Daily budget for the chart
        val totalIncome = transactions.filter { it.type == "income" }.sumOf { it.amount }
        val totalAdded = transactions.filter { it.type == "adjustment_add" }.sumOf { it.amount }
        val totalSubtracted = transactions.filter { it.type == "adjustment" }.sumOf { it.amount }
        val balance = totalIncome + totalAdded - totalSpent - totalSubtracted
        val dailyBudget = calculateDailyBudgetUseCase(balance + totalSpent, end, start)

        _uiState.update {
            it.copy(
                analyticsData = AnalyticsData(
                    averageSpendPerDay = avgPerDay,
                    totalSpendingCount = expenses.size,
                    maxSpend = maxEntry?.let { e -> SpendRecord(e.value, e.key) },
                    minSpend = minEntry?.let { e -> SpendRecord(e.value, e.key) },
                    dailySpending = dailySpending,
                    tagBreakdown = tagBreakdown,
                    dailyBudget = dailyBudget
                ),
                isLoading = false
            )
        }
    }
}
