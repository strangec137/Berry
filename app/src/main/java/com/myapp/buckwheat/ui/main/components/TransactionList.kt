package com.myapp.buckwheat.ui.main.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.domain.model.Transaction
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import com.myapp.buckwheat.ui.theme.AdjustmentBlue
import com.myapp.buckwheat.ui.theme.ExpenseRed
import com.myapp.buckwheat.ui.theme.IncomeGreen
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionList(
    transactions: List<Transaction>,
    currencySymbol: String,
    formatAmount: FormatAmountUseCase,
    onDelete: (Long) -> Unit,
    modifier: Modifier = Modifier,
    emptyContent: @Composable (() -> Unit)? = null,
    onSeeAllClick: (() -> Unit)? = null
) {
    if (transactions.isEmpty()) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (emptyContent != null) {
                emptyContent()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = "No spending yet",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Your transactions will appear here",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    )
                }
            }
        }
        return
    }

    var transactionToDelete by remember { mutableStateOf<Long?>(null) }
    var showSecondConfirmation by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = transactions,
            key = { it.id }
        ) { transaction ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    if (value == SwipeToDismissBoxValue.EndToStart) {
                        transactionToDelete = transaction.id
                        false // Don't dismiss — show dialog instead
                    } else false
                }
            )

            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    val color by animateColorAsState(
                        targetValue = when (dismissState.targetValue) {
                            SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
                            else -> Color.Transparent
                        },
                        label = "dismissBg"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color, MaterialTheme.shapes.medium)
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.onError
                        )
                    }
                },
                enableDismissFromStartToEnd = false,
                content = {
                    TransactionItem(
                        transaction = transaction,
                        currencySymbol = currencySymbol,
                        formatAmount = formatAmount
                    )
                }
            )
        }
        
        if (onSeeAllClick != null && transactions.isNotEmpty()) {
            item {
                TextButton(
                    onClick = onSeeAllClick,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text("See all transactions →")
                }
            }
        }
    }

    // First delete confirmation dialog
    if (transactionToDelete != null && !showSecondConfirmation) {
        AlertDialog(
            onDismissRequest = { transactionToDelete = null },
            title = { Text("Delete this transaction?") },
            confirmButton = {
                TextButton(
                    onClick = { showSecondConfirmation = true },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { transactionToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Second delete confirmation dialog
    if (showSecondConfirmation && transactionToDelete != null) {
        AlertDialog(
            onDismissRequest = {
                showSecondConfirmation = false
                transactionToDelete = null
            },
            title = { Text("Are you sure?") },
            text = { Text("This transaction will be permanently deleted.") },
            confirmButton = {
                Button(
                    onClick = {
                        transactionToDelete?.let { onDelete(it) }
                        showSecondConfirmation = false
                        transactionToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showSecondConfirmation = false
                    transactionToDelete = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    currencySymbol: String,
    formatAmount: FormatAmountUseCase,
    modifier: Modifier = Modifier
) {
    val isIncome = transaction.type == "income"
    val isAdjustment = transaction.type == "adjustment"
    val isAdjustmentAdd = transaction.type == "adjustment_add"
    val icon = when {
        isAdjustmentAdd -> Icons.Default.AddCircleOutline
        isAdjustment -> Icons.Default.RemoveCircleOutline
        isIncome -> Icons.Default.ArrowUpward
        else -> Icons.Default.ArrowDownward
    }
    val iconColor = when {
        isAdjustmentAdd -> IncomeGreen
        isAdjustment -> AdjustmentBlue
        isIncome -> IncomeGreen
        else -> ExpenseRed
    }
    val prefix = when {
        isIncome || isAdjustmentAdd -> "+"
        else -> "-"
    }
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = transaction.type,
                tint = iconColor,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.tag ?: when {
                        isAdjustmentAdd -> "Budget Added \u2795"
                        isAdjustment -> "Budget Adjusted \u2796"
                        isIncome -> "Income"
                        else -> "Expense"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = transaction.time.format(timeFormat),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }

            Text(
                text = "$prefix${formatAmount.formatFull(transaction.amount, currencySymbol)}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = iconColor
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagInput(
    tag: String,
    onTagChanged: (String) -> Unit,
    suggestions: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val filteredSuggestions = remember(tag, suggestions) {
        if (tag.isBlank()) emptyList()
        else suggestions.filter { it.contains(tag, ignoreCase = true) }.take(5)
    }

    ExposedDropdownMenuBox(
        expanded = expanded && filteredSuggestions.isNotEmpty(),
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = tag,
            onValueChange = {
                onTagChanged(it)
                expanded = true
            },
            label = { Text("Tag") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors()
        )

        ExposedDropdownMenu(
            expanded = expanded && filteredSuggestions.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            filteredSuggestions.forEach { suggestion ->
                DropdownMenuItem(
                    text = { Text(suggestion) },
                    onClick = {
                        onTagChanged(suggestion)
                        expanded = false
                    }
                )
            }
        }
    }
}
