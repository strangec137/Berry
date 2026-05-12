package com.myapp.buckwheat.ui.analytics

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import com.myapp.buckwheat.ui.analytics.components.SpendingCalendarChart
import com.myapp.buckwheat.ui.analytics.components.SummaryCards
import com.myapp.buckwheat.ui.analytics.components.TagsPieChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    onNavigateBack: () -> Unit,
    viewModel: AnalyticsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formatAmount = FormatAmountUseCase()
    var showRobinDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analytics") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Easter Egg: Robin Poneglyph Banner
            Card(
                onClick = { showRobinDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = com.myapp.buckwheat.R.drawable.robin_poneglyph),
                        contentDescription = "Robin Poneglyph",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Robin is reading the financial Poneglyph... 📜",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Period selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "current" to "Current Period",
                    "30days" to "Last 30 Days",
                    "alltime" to "All Time"
                ).forEach { (key, label) ->
                    FilterChip(
                        selected = uiState.selectedPeriod == key,
                        onClick = { viewModel.selectPeriod(key) },
                        label = { Text(label, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(32.dp)
                )
            } else {
                // Summary cards
                SummaryCards(
                    analyticsData = uiState.analyticsData,
                    currencySymbol = uiState.currencySymbol,
                    formatAmount = formatAmount
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Calendar chart
                Text(
                    text = "Spending Calendar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                SpendingCalendarChart(
                    dailySpending = uiState.analyticsData.dailySpending,
                    dailyBudget = uiState.analyticsData.dailyBudget,
                    periodStart = uiState.periodStart,
                    periodEnd = uiState.periodEnd
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Chart shows how much you spent every day relative to your daily budget",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Pie chart
                Text(
                    text = "Spending by Category",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (uiState.analyticsData.tagBreakdown.isEmpty() ||
                    (uiState.analyticsData.tagBreakdown.size == 1 &&
                            uiState.analyticsData.tagBreakdown.containsKey("Untagged"))
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text(
                            text = "Use tags to see chart by categories",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                } else {
                    TagsPieChart(
                        tagBreakdown = uiState.analyticsData.tagBreakdown,
                        currencySymbol = uiState.currencySymbol,
                        formatAmount = formatAmount
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    if (showRobinDialog) {
        AlertDialog(
            onDismissRequest = { showRobinDialog = false },
            title = { Text("The Poneglyph reveals...") },
            text = {
                Text(
                    text = "You've made ${uiState.totalTransactionsAllTime} moves\non the Grand Line of finance! ⚓",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(onClick = { showRobinDialog = false }) {
                    Text("Fascinating! 🌸")
                }
            }
        )
    }
}
