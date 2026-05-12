package com.myapp.buckwheat.domain.usecase

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class CalculateDailyBudgetUseCase @Inject constructor() {

    operator fun invoke(
        balance: Double,
        endDate: LocalDate,
        today: LocalDate = LocalDate.now()
    ): Double {
        val daysLeft = ChronoUnit.DAYS.between(today, endDate).toInt() + 1  // include today
        return if (daysLeft <= 0) 0.0 else balance / daysLeft
    }
}
