package com.myapp.buckwheat.domain.model

import java.time.LocalDate

data class Period(
    val id: Long = 0,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val totalIncome: Double = 0.0,
    val totalSpent: Double = 0.0,
    val currency: String = "TK"
)
