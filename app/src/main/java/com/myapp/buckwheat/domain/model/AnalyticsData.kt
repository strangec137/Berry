package com.myapp.buckwheat.domain.model

import java.time.LocalDate

data class AnalyticsData(
    val averageSpendPerDay: Double = 0.0,
    val totalSpendingCount: Int = 0,
    val maxSpend: SpendRecord? = null,
    val minSpend: SpendRecord? = null,
    val dailySpending: Map<LocalDate, Double> = emptyMap(),
    val tagBreakdown: Map<String, Double> = emptyMap(),
    val dailyBudget: Double = 0.0
)

data class SpendRecord(
    val amount: Double,
    val date: LocalDate
)
