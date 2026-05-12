package com.myapp.buckwheat.ui.analytics.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.domain.model.AnalyticsData
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import java.time.format.DateTimeFormatter

@Composable
fun SummaryCards(
    analyticsData: AnalyticsData,
    currencySymbol: String,
    formatAmount: FormatAmountUseCase,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val dateFormatter = DateTimeFormatter.ofPattern("MMM d")

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                label = "Average spend/day",
                value = formatAmount(analyticsData.averageSpendPerDay, currencySymbol),
                visible = visible,
                delayMs = 0,
                modifier = Modifier.weight(1f)
            )
            SummaryCard(
                label = "Total spending",
                value = analyticsData.totalSpendingCount.toString(),
                visible = visible,
                delayMs = 100,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                label = "Maximum spend",
                value = if (analyticsData.maxSpend != null)
                    formatAmount(analyticsData.maxSpend.amount, currencySymbol)
                else "—",
                subtitle = analyticsData.maxSpend?.date?.format(dateFormatter),
                visible = visible,
                delayMs = 200,
                modifier = Modifier.weight(1f)
            )
            SummaryCard(
                label = "Minimum spend",
                value = if (analyticsData.minSpend != null)
                    formatAmount(analyticsData.minSpend.amount, currencySymbol)
                else "—",
                subtitle = analyticsData.minSpend?.date?.format(dateFormatter),
                visible = visible,
                delayMs = 300,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SummaryCard(
    label: String,
    value: String,
    visible: Boolean,
    delayMs: Int,
    modifier: Modifier = Modifier,
    subtitle: String? = null
) {
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = delayMs
        ),
        label = "cardAlpha"
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = alpha)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
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
                fontWeight = FontWeight.Bold
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}
