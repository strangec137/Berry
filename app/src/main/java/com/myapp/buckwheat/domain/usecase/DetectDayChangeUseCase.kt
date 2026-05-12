package com.myapp.buckwheat.domain.usecase

import com.myapp.buckwheat.data.repository.SettingsRepository
import com.myapp.buckwheat.data.repository.TransactionRepository
import java.time.LocalDate
import javax.inject.Inject

data class DayChangeResult(
    val dayChanged: Boolean,
    val surplus: Double,
    val yesterdayDate: LocalDate?,
    val residueMethod: String
)

class DetectDayChangeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val transactionRepository: TransactionRepository,
    private val calculateDailyBudgetUseCase: CalculateDailyBudgetUseCase,
    private val calculateBalanceUseCase: CalculateBalanceUseCase
) {
    suspend operator fun invoke(): DayChangeResult {
        val today = LocalDate.now()
        val lastDateStr = settingsRepository.getPeriodStartSync()
        val endDateStr = settingsRepository.getPeriodEndSync()

        if (lastDateStr.isEmpty() || endDateStr.isEmpty()) {
            return DayChangeResult(false, 0.0, null, "ask")
        }

        val lastKnownDateFlow = settingsRepository.lastKnownDate
        var lastKnownStr = ""
        lastKnownDateFlow.collect { lastKnownStr = it; return@collect }

        if (lastKnownStr.isEmpty()) {
            settingsRepository.setLastKnownDate(today.toString())
            return DayChangeResult(false, 0.0, null, "ask")
        }

        val lastDate = LocalDate.parse(lastKnownStr)
        if (!lastDate.isBefore(today)) {
            return DayChangeResult(false, 0.0, null, "ask")
        }

        // Day changed — calculate yesterday's surplus
        val endDate = LocalDate.parse(endDateStr)
        val startDate = LocalDate.parse(lastDateStr)

        val allTransactions = transactionRepository.getTransactionsBetweenSync(
            startDate.toString(), lastDate.toString()
        )
        val balanceUpToYesterday = calculateBalanceUseCase(allTransactions)
        val yesterdayBudget = calculateDailyBudgetUseCase(balanceUpToYesterday, endDate, lastDate)

        val yesterdayExpenses = transactionRepository.getTransactionsOnDateSync(lastDate.toString())
            .filter { it.type == "expense" }
            .sumOf { it.amount }

        val surplus = yesterdayBudget - yesterdayExpenses

        // Update last known date
        settingsRepository.setLastKnownDate(today.toString())

        // Get residue method
        var residueMethod = "ask"
        settingsRepository.residueMethod.collect { residueMethod = it; return@collect }

        return DayChangeResult(
            dayChanged = true,
            surplus = if (surplus > 0) surplus else 0.0,
            yesterdayDate = lastDate,
            residueMethod = residueMethod
        )
    }
}
