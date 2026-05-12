package com.myapp.buckwheat.widget.minimal
import androidx.compose.ui.unit.dp
import android.content.Context
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
import com.myapp.buckwheat.data.local.AppPreferences
import com.myapp.buckwheat.data.local.AppDatabase
import androidx.room.Room
import com.myapp.buckwheat.ui.theme.md_theme_light_onSurface
import com.myapp.buckwheat.ui.theme.md_theme_light_surface

class MinimalWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val prefs = AppPreferences(context)
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "buckwheat_database").build()

        val startDate = prefs.getPeriodStartSync()
        val endDate = prefs.getPeriodEndSync()
        val symbol = prefs.getCurrencySymbolSync()

        val balance = if (startDate.isNotEmpty() && endDate.isNotEmpty()) {
            val totalIncome = db.transactionDao().getTotalIncome(startDate, endDate) ?: 0.0
            val totalExpenses = db.transactionDao().getTotalExpenses(startDate, endDate) ?: 0.0
            val totalAdded = db.transactionDao().getTotalAdjustmentAdded(startDate, endDate) ?: 0.0
            val totalSubtracted = db.transactionDao().getTotalAdjustmentSubtracted(startDate, endDate) ?: 0.0
            totalIncome + totalAdded - totalExpenses - totalSubtracted
        } else {
            0.0
        }

        val displayAmount = when {
            balance >= 1_000_000 -> "${String.format("%.1f", balance / 1_000_000)}M"
            balance >= 1_000 -> "${String.format("%.1f", balance / 1_000)}K"
            else -> String.format("%.0f", balance)
        }

        provideContent {
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .background(md_theme_light_surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$symbol $displayAmount left",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = ColorProvider(md_theme_light_onSurface)
                    )
                )
            }
        }
    }
}

class MinimalWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MinimalWidget()
}
