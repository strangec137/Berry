package com.myapp.buckwheat.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import com.myapp.buckwheat.ui.theme.WarningAmber

@Composable
fun WalletBar(
    dailyBudget: Double,
    daysLeft: Int,
    currencySymbol: String,
    todaySpent: Double,
    formatAmount: FormatAmountUseCase,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spendRatio = if (dailyBudget > 0) todaySpent / dailyBudget else 0.0
    val budgetColor = when {
        spendRatio > 1.0 -> MaterialTheme.colorScheme.error
        spendRatio > 0.8 -> WarningAmber
        else -> MaterialTheme.colorScheme.primary
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Daily budget",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
                Text(
                    text = formatAmount.formatFull(dailyBudget, currencySymbol),
                    style = MaterialTheme.typography.titleLarge,
                    color = budgetColor
                )
            }

            Text(
                text = "$daysLeft days left",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
