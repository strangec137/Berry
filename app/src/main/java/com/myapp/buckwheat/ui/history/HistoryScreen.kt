package com.myapp.buckwheat.ui.history

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.buckwheat.domain.model.Transaction
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase
import com.myapp.buckwheat.ui.main.components.TransactionItem
import com.myapp.buckwheat.ui.theme.AdjustmentBlue
import com.myapp.buckwheat.ui.theme.ExpenseRed
import com.myapp.buckwheat.ui.theme.IncomeGreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onNavigateBack: () -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formatAmount = FormatAmountUseCase()
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy")

    var transactionToDelete by remember { mutableStateOf<Long?>(null) }
    var showSecondConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
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
        ) {
            // Search bar
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                placeholder = { Text("Search by tag...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = MaterialTheme.shapes.medium
            )

            // Filter chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "all" to "All",
                    "income" to "Income",
                    "expense" to "Expenses",
                    "adjustment" to "Adjustments"
                ).forEach { (key, label) ->
                    FilterChip(
                        selected = uiState.filterType == key,
                        onClick = { viewModel.setFilter(key) },
                        label = { Text(label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (uiState.filteredTransactions.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.AccountBalanceWallet,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No spending yet",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                }
            } else {
                // Group by date
                val grouped = uiState.filteredTransactions.groupBy { it.date }
                    .toSortedMap(compareByDescending { it })

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    grouped.forEach { (date, transactions) ->
                        item(key = "header_$date") {
                            DateHeader(
                                date = date,
                                transactions = transactions,
                                currencySymbol = uiState.currencySymbol,
                                formatAmount = formatAmount,
                                dateFormatter = dateFormatter
                            )
                        }

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
                                        label = "bg"
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
                                enableDismissFromStartToEnd = false
                            ) {
                                TransactionItem(
                                    transaction = transaction,
                                    currencySymbol = uiState.currencySymbol,
                                    formatAmount = formatAmount
                                )
                            }
                        }
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
                            transactionToDelete?.let { viewModel.deleteTransaction(it) }
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
}

@Composable
private fun DateHeader(
    date: LocalDate,
    transactions: List<Transaction>,
    currencySymbol: String,
    formatAmount: FormatAmountUseCase,
    dateFormatter: DateTimeFormatter
) {
    val dayTotal = transactions
        .filter { it.type == "expense" }
        .sumOf { it.amount }
    val dayIncome = transactions
        .filter { it.type == "income" }
        .sumOf { it.amount }
    val dayAdjustments = transactions
        .filter { it.type == "adjustment" }
        .sumOf { it.amount }
    val dayAdjustmentAdds = transactions
        .filter { it.type == "adjustment_add" }
        .sumOf { it.amount }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = date.format(dateFormatter),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Column(horizontalAlignment = Alignment.End) {
            if (dayTotal > 0) {
                Text(
                    text = "-${formatAmount.formatFull(dayTotal, currencySymbol)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = ExpenseRed
                )
            }
            if (dayIncome > 0) {
                Text(
                    text = "+${formatAmount.formatFull(dayIncome, currencySymbol)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = IncomeGreen
                )
            }
            if (dayAdjustmentAdds > 0) {
                Text(
                    text = "+${formatAmount.formatFull(dayAdjustmentAdds, currencySymbol)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = IncomeGreen
                )
            }
            if (dayAdjustments > 0) {
                Text(
                    text = "-${formatAmount.formatFull(dayAdjustments, currencySymbol)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = AdjustmentBlue
                )
            }
        }
    }
}
