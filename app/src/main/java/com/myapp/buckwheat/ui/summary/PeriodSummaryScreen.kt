package com.myapp.buckwheat.ui.summary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import com.myapp.buckwheat.ui.analytics.AnalyticsViewModel
import com.myapp.buckwheat.ui.analytics.components.SpendingCalendarChart
import com.myapp.buckwheat.ui.analytics.components.TagsPieChart
import com.myapp.buckwheat.ui.theme.IncomeGreen
import java.time.format.DateTimeFormatter

@Composable
fun PeriodSummaryScreen(
    onSetupNewPeriod: () -> Unit,
    viewModel: AnalyticsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formatAmount = FormatAmountUseCase()
    val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.TrendingUp,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Here are the statistics\nfor this period",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (uiState.periodStart != null && uiState.periodEnd != null) {
                Text(
                    text = "${uiState.periodStart!!.format(dateFormatter)} — ${uiState.periodEnd!!.format(dateFormatter)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Summary cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryStatCard(
                    label = "Total Income",
                    value = formatAmount.formatFull(
                        uiState.analyticsData.let { it.averageSpendPerDay * 30 }, // approximate
                        uiState.currencySymbol
                    ),
                    color = IncomeGreen,
                    modifier = Modifier.weight(1f)
                )
                SummaryStatCard(
                    label = "Total Spent",
                    value = formatAmount.formatFull(
                        uiState.analyticsData.dailySpending.values.sum(),
                        uiState.currencySymbol
                    ),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val totalSpent = uiState.analyticsData.dailySpending.values.sum()
                val avgDay = uiState.analyticsData.averageSpendPerDay

                SummaryStatCard(
                    label = "Average/Day",
                    value = formatAmount(avgDay, uiState.currencySymbol),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                SummaryStatCard(
                    label = "Transactions",
                    value = uiState.analyticsData.totalSpendingCount.toString(),
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Calendar chart
            SpendingCalendarChart(
                dailySpending = uiState.analyticsData.dailySpending,
                dailyBudget = uiState.analyticsData.dailyBudget,
                periodStart = uiState.periodStart,
                periodEnd = uiState.periodEnd
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Pie chart
            if (uiState.analyticsData.tagBreakdown.isNotEmpty()) {
                TagsPieChart(
                    tagBreakdown = uiState.analyticsData.tagBreakdown,
                    currencySymbol = uiState.currencySymbol,
                    formatAmount = formatAmount
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // New period button
            Button(
                onClick = onSetupNewPeriod,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Set up new budget",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SummaryStatCard(
    label: String,
    value: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}
