package com.myapp.buckwheat.ui.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase

@Composable
fun ResidueDialog(
    surplus: Double,
    dailyBudget: Double,
    daysLeft: Int,
    currencySymbol: String,
    formatAmount: FormatAmountUseCase,
    onAccept: (addToToday: Boolean, remember: Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    var addToToday by remember { mutableStateOf(true) }
    var rememberChoice by remember { mutableStateOf(false) }

    val todayAmount = dailyBudget + surplus
    val distributeAmount = if (daysLeft > 0) (dailyBudget * daysLeft + surplus) / daysLeft else dailyBudget

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Congratulations! You saved ${formatAmount.formatFull(surplus, currencySymbol)}")
        },
        text = {
            Column {
                Text(
                    text = "How would you like to distribute it?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Option A
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = addToToday,
                        onClick = { addToToday = true }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Add to today's budget",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Get ${formatAmount.formatFull(todayAmount, currencySymbol)} for today",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                // Option B
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = !addToToday,
                        onClick = { addToToday = false }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Split by remaining days",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Get ${formatAmount.formatFull(distributeAmount, currencySymbol)} per day",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Remember checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = rememberChoice,
                        onCheckedChange = { rememberChoice = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Remember my choice",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = { onAccept(addToToday, rememberChoice) }) {
                Text("Accept")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Later")
            }
        }
    )
}
