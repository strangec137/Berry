package com.myapp.buckwheat.widget.extend

import android.content.Context
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.room.Room
import com.myapp.buckwheat.data.local.AppDatabase
import com.myapp.buckwheat.data.local.AppPreferences
import com.myapp.buckwheat.ui.theme.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ExtendWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val prefs = AppPreferences(context)
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "buckwheat_database").build()

        val startDate = prefs.getPeriodStartSync()
        val endDate = prefs.getPeriodEndSync()
        val symbol = prefs.getCurrencySymbolSync()

        val today = LocalDate.now()

        var dailyBudget = 0.0
        var balance = 0.0
        var daysLeft = 0
        var periodProgress = 0f

        if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
            val start = LocalDate.parse(startDate)
            val end = LocalDate.parse(endDate)
            val totalIncome = db.transactionDao().getTotalIncome(startDate, endDate) ?: 0.0
            val totalExpenses = db.transactionDao().getTotalExpenses(startDate, endDate) ?: 0.0
            val totalAdded = db.transactionDao().getTotalAdjustmentAdded(startDate, endDate) ?: 0.0
            val totalSubtracted = db.transactionDao().getTotalAdjustmentSubtracted(startDate, endDate) ?: 0.0
            balance = totalIncome + totalAdded - totalExpenses - totalSubtracted

            daysLeft = (ChronoUnit.DAYS.between(today, end).toInt() + 1).coerceAtLeast(0)
            dailyBudget = if (daysLeft > 0) balance / daysLeft else 0.0

            val totalDays = ChronoUnit.DAYS.between(start, end).toInt() + 1
            val daysPassed = ChronoUnit.DAYS.between(start, today).toInt() + 1
            periodProgress = if (totalDays > 0) daysPassed.toFloat() / totalDays else 0f
        }

        val displayBudget = when {
            dailyBudget >= 1_000_000 -> "${String.format("%.1f", dailyBudget / 1_000_000)}M"
            dailyBudget >= 1_000 -> "${String.format("%.1f", dailyBudget / 1_000)}K"
            else -> String.format("%.0f", dailyBudget)
        }

        val displayBalance = when {
            balance >= 1_000_000 -> "${String.format("%.1f", balance / 1_000_000)}M"
            balance >= 1_000 -> "${String.format("%.1f", balance / 1_000)}K"
            else -> String.format("%.0f", balance)
        }

        provideContent {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(md_theme_light_surface)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = GlanceModifier.defaultWeight()) {
                        Text(
                            text = "Today",
                            style = TextStyle(
                                color = ColorProvider(md_theme_light_onSurface.copy(alpha = 0.6f))
                            )
                        )
                        Text(
                            text = "$symbol $displayBudget",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = ColorProvider(md_theme_light_primary)
                            )
                        )
                    }
                    Text(
                        text = "$daysLeft days left",
                        style = TextStyle(
                            color = ColorProvider(md_theme_light_onSurface.copy(alpha = 0.6f))
                        )
                    )
                }

                Spacer(modifier = GlanceModifier.height(8.dp))

                // Progress simplified as text (Glance doesn't have LinearProgressIndicator)
                Text(
                    text = "Left: $symbol $displayBalance  •  ${(periodProgress * 100).toInt()}% of period",
                    style = TextStyle(
                        color = ColorProvider(md_theme_light_onSurface.copy(alpha = 0.5f))
                    )
                )
            }
        }
    }
}

class ExtendWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ExtendWidget()
}
