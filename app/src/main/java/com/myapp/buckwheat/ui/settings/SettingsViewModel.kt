package com.myapp.buckwheat.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Intent
import com.myapp.buckwheat.data.repository.PeriodRepository
import com.myapp.buckwheat.data.repository.SettingsRepository
import com.myapp.buckwheat.data.repository.TransactionRepository
import com.myapp.buckwheat.domain.usecase.ExportCsvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val themeMode: String = "system",
    val currencyCode: String = "BDT",
    val currencySymbol: String = "TK",
    val residueMethod: String = "ask",
    val hideOverspend: Boolean = false,
    val periodStart: String = "",
    val periodEnd: String = ""
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val transactionRepository: TransactionRepository,
    private val periodRepository: PeriodRepository,
    private val exportCsvUseCase: ExportCsvUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    val themeMode: StateFlow<String> = settingsRepository.themeMode
        .stateIn(viewModelScope, SharingStarted.Eagerly, "system")

    init {
        viewModelScope.launch {
            combine(
                settingsRepository.themeMode,
                settingsRepository.currencyCode,
                settingsRepository.currencySymbol,
                settingsRepository.residueMethod,
                settingsRepository.hideOverspend,
                settingsRepository.periodStart,
                settingsRepository.periodEnd
            ) { values ->
                SettingsUiState(
                    themeMode = values[0] as String,
                    currencyCode = values[1] as String,
                    currencySymbol = values[2] as String,
                    residueMethod = values[3] as String,
                    hideOverspend = values[4] as Boolean,
                    periodStart = values[5] as String,
                    periodEnd = values[6] as String
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            settingsRepository.setThemeMode(mode)
        }
    }

    fun setResidueMethod(method: String) {
        viewModelScope.launch {
            settingsRepository.setResidueMethod(method)
        }
    }

    fun setHideOverspend(hide: Boolean) {
        viewModelScope.launch {
            settingsRepository.setHideOverspend(hide)
        }
    }

    fun setCurrency(code: String, symbol: String) {
        viewModelScope.launch {
            settingsRepository.setCurrencyCode(code)
            settingsRepository.setCurrencySymbol(symbol)
        }
    }

    fun exportCsv(onResult: (Intent) -> Unit) {
        viewModelScope.launch {
            try {
                val state = _uiState.value
                val intent = exportCsvUseCase(state.periodStart, state.periodEnd)
                onResult(intent)
            } catch (_: Exception) {}
        }
    }

    fun setupPeriod(startDate: String, endDate: String) {
        viewModelScope.launch {
            settingsRepository.setPeriodStart(startDate)
            settingsRepository.setPeriodEnd(endDate)
            settingsRepository.setLastKnownDate(java.time.LocalDate.now().toString())
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            settingsRepository.setOnboardingDone(true)
        }
    }

    fun setInitialBudget(amount: Double) {
        viewModelScope.launch {
            if (amount > 0) {
                val state = _uiState.value
                val now = java.time.LocalDateTime.now()
                val transaction = com.myapp.buckwheat.data.local.entity.TransactionEntity(
                    type = "income",
                    amount = amount,
                    currency = state.currencyCode,
                    tag = "Starting Budget",
                    note = "Starting Budget",
                    date = now.toLocalDate().toString(),
                    time = now.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")),
                    createdAt = System.currentTimeMillis()
                )
                transactionRepository.insert(transaction)
            }
        }
    }

    fun clearAllData(onDone: () -> Unit) {
        viewModelScope.launch {
            transactionRepository.deleteAll()
            periodRepository.deleteAll()
            settingsRepository.clearAll()
            onDone()
        }
    }
}
