package com.myapp.buckwheat.ui.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import com.myapp.buckwheat.ui.main.MainUiState
import com.myapp.buckwheat.ui.theme.IncomeGreen
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletBottomSheet(
    uiState: MainUiState,
    formatAmount: FormatAmountUseCase,
    onDismiss: () -> Unit,
    onEditPeriod: () -> Unit,
    onApplyPeriod: (LocalDate, LocalDate) -> Unit,
    onCancelEdit: () -> Unit,
    onAddIncome: () -> Unit,
    onFinishEarly: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Budget",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onEditPeriod) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit period",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Edit period")
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            // Budget overview
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Starting budget",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = formatAmount.formatFull(uiState.totalIncome, uiState.currencySymbol),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = IncomeGreen
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Left",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = formatAmount.formatFull(uiState.currentBalance, uiState.currencySymbol),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (uiState.currentBalance >= 0)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            // Stats row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(
                    label = "For today",
                    value = formatAmount.formatFull(uiState.dailyBudget, uiState.currencySymbol)
                )
                StatItem(
                    label = "Days left",
                    value = uiState.daysLeft.toString()
                )
                StatItem(
                    label = "Avg spend",
                    value = "${formatAmount(uiState.averageSpend, uiState.currencySymbol)}/day"
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            // Period dates
            if (uiState.periodStart != null && uiState.periodEnd != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "From: ${uiState.periodStart.format(dateFormatter)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "To: ${uiState.periodEnd.format(dateFormatter)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            // Edit period form (inline)
            if (uiState.isEditingPeriod) {
                Spacer(modifier = Modifier.height(12.dp))
                EditPeriodForm(
                    currentStart = uiState.periodStart ?: LocalDate.now(),
                    currentEnd = uiState.periodEnd ?: LocalDate.now().plusMonths(1),
                    onApply = { start, end ->
                        onApplyPeriod(start, end)
                        scope.launch {
                            snackbarHostState.showSnackbar("Period updated")
                        }
                    },
                    onCancel = onCancelEdit
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            // Add Income button
            Button(
                onClick = onAddIncome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "+ Add Income",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Finish early
            TextButton(
                onClick = {
                    onFinishEarly()
                    scope.launch {
                        snackbarHostState.showSnackbar("Period finished")
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Finish early",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}
