package com.myapp.buckwheat.ui.analytics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.ui.theme.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun SpendingCalendarChart(
    dailySpending: Map<LocalDate, Double>,
    dailyBudget: Double,
    periodStart: LocalDate?,
    periodEnd: LocalDate?,
    modifier: Modifier = Modifier
) {
    if (periodStart == null || periodEnd == null) return

    val today = LocalDate.now()
    val days = ChronoUnit.DAYS.between(periodStart, periodEnd).toInt() + 1
    val columns = 7
    val rows = (days + columns - 1) / columns
    var selectedDay by remember { mutableStateOf<LocalDate?>(null) }

    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val grayColor = if (isDark) CalendarGrayDark else CalendarGray

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((rows * 36 + (rows - 1) * 4).dp)
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            val cellW = size.width.toFloat() / columns
                            val cellH = (size.height.toFloat()) / rows
                            val col = (offset.x / cellW).toInt()
                            val row = (offset.y / cellH).toInt()
                            val dayIndex = row * columns + col
                            if (dayIndex < days) {
                                selectedDay = periodStart.plusDays(dayIndex.toLong())
                            }
                        }
                    }
            ) {
                val cellWidth = size.width / columns
                val cellHeight = size.height / rows
                val cornerRadius = CornerRadius(8f, 8f)

                for (i in 0 until days) {
                    val row = i / columns
                    val col = i % columns
                    val date = periodStart.plusDays(i.toLong())
                    val spent = dailySpending[date] ?: 0.0

                    val color = when {
                        date.isAfter(today) -> grayColor.copy(alpha = 0.3f)
                        spent == 0.0 -> grayColor.copy(alpha = 0.3f)
                        dailyBudget <= 0 -> CalendarGreen
                        spent <= dailyBudget * 0.8 -> CalendarGreen
                        spent <= dailyBudget -> CalendarAmber
                        else -> CalendarRed
                    }

                    drawRoundRect(
                        color = color,
                        topLeft = Offset(
                            col * cellWidth + 2f,
                            row * cellHeight + 2f
                        ),
                        size = Size(cellWidth - 4f, cellHeight - 4f),
                        cornerRadius = cornerRadius
                    )
                }
            }

            // Tooltip for selected day
            if (selectedDay != null) {
                val spent = dailySpending[selectedDay] ?: 0.0
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$selectedDay: ${String.format("%.0f", spent)} spent",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

private fun Color.luminance(): Float {
    val r = red * 0.2126f
    val g = green * 0.7152f
    val b = blue * 0.0722f
    return r + g + b
}
