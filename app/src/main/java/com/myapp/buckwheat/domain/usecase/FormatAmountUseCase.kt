package com.myapp.buckwheat.domain.usecase

import javax.inject.Inject

class FormatAmountUseCase @Inject constructor() {

    operator fun invoke(amount: Double, symbol: String): String {
        val abs = Math.abs(amount)
        val formatted = when {
            abs >= 1_000_000_000 -> "${format(abs / 1_000_000_000, 1)}B"
            abs >= 1_000_000 -> "${format(abs / 1_000_000, 1)}M"
            abs >= 1_000 -> "${format(abs / 1_000, 1)}K"
            else -> format(abs, if (abs == abs.toLong().toDouble()) 0 else 2)
        }
        val prefix = if (amount < 0) "-" else ""
        return "$prefix$symbol $formatted"
    }

    fun formatFull(amount: Double, symbol: String): String {
        val abs = Math.abs(amount)
        val formatted = if (abs == abs.toLong().toDouble()) {
            String.format("%,.0f", abs)
        } else {
            String.format("%,.2f", abs)
        }
        val prefix = if (amount < 0) "-" else ""
        return "$prefix$symbol $formatted"
    }

    private fun format(value: Double, decimals: Int): String {
        return String.format("%.${decimals}f", value)
    }
}
