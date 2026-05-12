package com.myapp.buckwheat.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.buckwheat.data.repository.SettingsRepository
import com.myapp.buckwheat.data.repository.TransactionRepository
import com.myapp.buckwheat.domain.model.Transaction
import com.myapp.buckwheat.domain.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class HistoryUiState(
    val transactions: List<Transaction> = emptyList(),
    val filteredTransactions: List<Transaction> = emptyList(),
    val filterType: String = "all",  // "all", "income", "expense"
    val searchQuery: String = "",
    val currencySymbol: String = "TK"
)

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            combine(
                settingsRepository.periodStart,
                settingsRepository.periodEnd,
                settingsRepository.currencySymbol
            ) { start, end, symbol ->
                Triple(start, end, symbol)
            }.collect { (startStr, endStr, symbol) ->
                if (startStr.isNotEmpty() && endStr.isNotEmpty()) {
                    transactionRepository.getTransactionsBetween(startStr, endStr)
                        .collect { entities ->
                            val transactions = entities.map { it.toDomain(symbol) }
                            _uiState.update { state ->
                                state.copy(
                                    transactions = transactions,
                                    currencySymbol = symbol,
                                    filteredTransactions = applyFilters(
                                        transactions,
                                        state.filterType,
                                        state.searchQuery
                                    )
                                )
                            }
                        }
                }
            }
        }
    }

    fun setFilter(type: String) {
        _uiState.update { state ->
            state.copy(
                filterType = type,
                filteredTransactions = applyFilters(state.transactions, type, state.searchQuery)
            )
        }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { state ->
            state.copy(
                searchQuery = query,
                filteredTransactions = applyFilters(state.transactions, state.filterType, query)
            )
        }
    }

    fun deleteTransaction(id: Long) {
        viewModelScope.launch {
            transactionRepository.deleteById(id)
        }
    }

    private fun applyFilters(
        transactions: List<Transaction>,
        filterType: String,
        searchQuery: String
    ): List<Transaction> {
        return transactions
            .filter { t ->
                when (filterType) {
                    "income" -> t.type == "income"
                    "expense" -> t.type == "expense"
                    "adjustment" -> t.type == "adjustment" || t.type == "adjustment_add"
                    else -> true
                }
            }
            .filter { t ->
                if (searchQuery.isBlank()) true
                else t.tag?.contains(searchQuery, ignoreCase = true) == true ||
                        t.note?.contains(searchQuery, ignoreCase = true) == true
            }
    }
}
