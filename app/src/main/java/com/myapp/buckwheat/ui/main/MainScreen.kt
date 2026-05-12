package com.myapp.buckwheat.ui.main

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.myapp.buckwheat.R
import com.myapp.buckwheat.ui.main.components.*
import com.myapp.buckwheat.ui.theme.IncomeGreen
import com.myapp.buckwheat.ui.theme.WarningAmber
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigateToAnalytics: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onPeriodEnded: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showZeroBalanceDialog by remember { mutableStateOf(false) }
    var showYonkoPopup by remember { mutableStateOf(false) }
    var lastKnownBalance by remember { mutableDoubleStateOf(Double.MAX_VALUE) }
    var showSanjiCooking by remember { mutableStateOf(false) }

    // Auto-dismiss Yonko popup after 3 seconds
    LaunchedEffect(showYonkoPopup) {
        if (showYonkoPopup) {
            delay(3000L)
            showYonkoPopup = false
        }
    }

    // Auto-dismiss Sanji cooking after 2.5 seconds
    LaunchedEffect(Unit) {
        viewModel.sanjiEvent.collect {
            showSanjiCooking = true
            delay(2500L)
            showSanjiCooking = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkDayChange()
    }

    LaunchedEffect(uiState.isPeriodEnded) {
        if (uiState.isPeriodEnded && uiState.onboardingDone) {
            onPeriodEnded()
        }
    }

    // Easter Egg: Zoro zero-balance popup
    LaunchedEffect(uiState.currentBalance) {
        if (lastKnownBalance != Double.MAX_VALUE &&
            lastKnownBalance > 0 &&
            uiState.currentBalance <= 0 &&
            !uiState.isLoading &&
            uiState.onboardingDone
        ) {
            showZeroBalanceDialog = true
        }
        lastKnownBalance = uiState.currentBalance
    }

    // Loading screen with Luffy
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.luffy_monkey),
                    contentDescription = "Luffy Loading",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    strokeWidth = 3.dp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Setting sail... 🏴‍☠️",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Berry",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToHistory) {
                        Icon(Icons.Default.History, contentDescription = "History")
                    }
                    IconButton(onClick = onNavigateToAnalytics) {
                        Icon(Icons.Default.Analytics, contentDescription = "Analytics")
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Daily Budget Panel (top) ---
            DailyBudgetPanel(
                dailyBudget = uiState.dailyBudget,
                daysLeft = uiState.daysLeft,
                todaySpent = uiState.todaySpent,
                currencySymbol = uiState.currencySymbol,
                formatAmount = viewModel.formatAmountUseCase,
                onClick = { viewModel.toggleWalletSheet() }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tag input
            TagInput(
                tag = uiState.inputTag,
                onTagChanged = { viewModel.onTagChanged(it) },
                suggestions = uiState.distinctTags,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Amount display
            AmountDisplay(
                amount = uiState.inputAmount,
                currencySymbol = uiState.currencySymbol,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Overspending message
            if (uiState.currentBalance < 0 && !uiState.hideOverspendWarn) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = "The budget is over — Do not worry, spending is never constant. Keep tracking to understand your real needs.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // Today's transactions (with straw_hat easter egg for empty state)
            TransactionList(
                transactions = uiState.todayTransactions,
                currencySymbol = uiState.currencySymbol,
                formatAmount = viewModel.formatAmountUseCase,
                onDelete = { viewModel.deleteTransaction(it) },
                modifier = Modifier.weight(1f).heightIn(min = 180.dp),
                onSeeAllClick = onNavigateToHistory,
                emptyContent = {
                    // Easter Egg: Straw hat empty state
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.straw_hat),
                            contentDescription = "Straw Hat",
                            modifier = Modifier.size(150.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No treasure yet, set sail! 🏴‍☠️",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )

            // Number pad
            NumberPad(
                onNumberClick = { viewModel.onNumberInput(it) },
                onBackspace = { viewModel.onBackspace() },
                onClear = { viewModel.onClear() },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Action buttons row: Add spent + Adjust Budget
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        // Easter Egg: Yonko-level spending popup
                        val inputAmt = uiState.inputAmount.toDoubleOrNull() ?: 0.0
                        val budget = uiState.dailyBudget
                        viewModel.addExpense()
                        if (budget > 0 && inputAmt > budget * 0.20) {
                            showYonkoPopup = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Add spent",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                OutlinedButton(
                    onClick = { viewModel.toggleAdjustDialog() },
                    modifier = Modifier.height(56.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(
                        Icons.Default.Tune,
                        contentDescription = "Adjust Budget",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Adjust",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    // Wallet Bottom Sheet
    if (uiState.showWalletSheet) {
        WalletBottomSheet(
            uiState = uiState,
            formatAmount = viewModel.formatAmountUseCase,
            onDismiss = { viewModel.toggleWalletSheet() },
            onEditPeriod = { viewModel.toggleEditPeriod() },
            onApplyPeriod = { start, end -> viewModel.updatePeriod(start, end) },
            onCancelEdit = { viewModel.toggleEditPeriod() },
            onAddIncome = { viewModel.toggleIncomeSheet(true) },
            onFinishEarly = { viewModel.finishPeriodEarly() },
            snackbarHostState = snackbarHostState
        )
    }

    // Add Income Sheet
    if (uiState.showIncomeSheet) {
        AddIncomeSheet(
            lastIncome = uiState.lastIncome,
            currencySymbol = uiState.currencySymbol,
            onDismiss = { viewModel.toggleIncomeSheet(false) },
            onSave = { amount, source, date, note ->
                viewModel.addIncome(amount, source, date, note)
            },
            snackbarHostState = snackbarHostState
        )
    }

    // Residue Dialog
    if (uiState.showResidueDialog) {
        ResidueDialog(
            surplus = uiState.residueSurplus,
            dailyBudget = uiState.dailyBudget,
            daysLeft = uiState.daysLeft,
            currencySymbol = uiState.currencySymbol,
            formatAmount = viewModel.formatAmountUseCase,
            onAccept = { addToToday, remember ->
                viewModel.handleResidue(addToToday, remember)
            },
            onDismiss = { viewModel.dismissResidueDialog() }
        )
    }

    // Budget Adjustment Dialog (Add & Subtract)
    if (uiState.showAdjustDialog) {
        BudgetAdjustmentDialog(
            currencySymbol = uiState.currencySymbol,
            onConfirm = { amount, isAddition, note ->
                viewModel.addBudgetAdjustment(amount, isAddition, note)
            },
            onDismiss = { viewModel.toggleAdjustDialog() }
        )
    }

    // Easter Egg: Zoro zero balance dialog
    if (showZeroBalanceDialog) {
        AlertDialog(
            onDismissRequest = { showZeroBalanceDialog = false },
            confirmButton = {
                TextButton(onClick = { showZeroBalanceDialog = false }) {
                    Text("OK 😅")
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zoro_thumbsdown),
                        contentDescription = "Zoro Broke",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Broke like Zoro after shopping 😅",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }

    // Easter Egg: Yonko-level spending popup with hahaha.png
    AnimatedVisibility(
        visible = showYonkoPopup,
        enter = fadeIn() + slideInVertically { -it },
        exit = fadeOut() + slideOutVertically { -it }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 80.dp, start = 24.dp, end = 24.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hahaha),
                        contentDescription = "Yonko Laugh",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "Yonko-level spending detected! ⚓",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
    }

    // Easter Egg: Devil Fruit Unlock Dialog
    if (uiState.showDevilFruitUnlock) {
        androidx.compose.ui.window.Dialog(
            onDismissRequest = { viewModel.dismissDevilFruitUnlock() },
            properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.devil_fruit2),
                        contentDescription = "Devil Fruit",
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "You ate a Devil Fruit! 🍎",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "You've gained the power to track your Berry!\nBut you can never swim again... 😅",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    Button(
                        onClick = { viewModel.dismissDevilFruitUnlock() },
                        modifier = Modifier.height(56.dp)
                    ) {
                        Text("Yosh! Let's go! 💪", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }

    // Easter Egg: Sanji Cooking overlay
    AnimatedVisibility(
        visible = showSanjiCooking,
        enter = fadeIn() + slideInVertically { it },
        exit = fadeOut() + slideOutVertically { it }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier.padding(bottom = 80.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = IncomeGreen.copy(alpha = 0.9f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sanji_cooking),
                        contentDescription = "Sanji Cooking",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "Sanji cooked up some Berry! 🍳⚓",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }
            }
        }
    }
}

// --- Daily Budget Panel (top of screen) ---
@Composable
private fun DailyBudgetPanel(
    dailyBudget: Double,
    daysLeft: Int,
    todaySpent: Double,
    currencySymbol: String,
    formatAmount: com.myapp.buckwheat.domain.usecase.FormatAmountUseCase,
    onClick: () -> Unit
) {
    val spendRatio = if (dailyBudget > 0) todaySpent / dailyBudget else 0.0
    val budgetColor = when {
        spendRatio > 1.0 -> MaterialTheme.colorScheme.error
        spendRatio > 0.8 -> WarningAmber
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Daily budget",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
                Text(
                    text = formatAmount.formatFull(dailyBudget, currencySymbol),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = budgetColor
                )
            }

            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "$daysLeft days left",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

// --- Budget Adjustment Dialog (Add & Subtract) ---
@Composable
private fun BudgetAdjustmentDialog(
    currencySymbol: String,
    onConfirm: (Double, Boolean, String?) -> Unit,
    onDismiss: () -> Unit
) {
    var amountText by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    var isAddition by remember { mutableStateOf(false) } // default to subtract
    val amount = amountText.toDoubleOrNull()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adjust Budget") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Add or subtract an amount from your balance. Use this for corrections, returns, or top-ups.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                // Add / Subtract toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Subtract button
                    FilterChip(
                        selected = !isAddition,
                        onClick = { isAddition = false },
                        label = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Subtract")
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.errorContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    // Add button
                    FilterChip(
                        selected = isAddition,
                        onClick = { isAddition = true },
                        label = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Add")
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = IncomeGreen.copy(alpha = 0.2f),
                            selectedLabelColor = IncomeGreen
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = amountText,
                    onValueChange = { amountText = it.filter { c -> c.isDigit() || c == '.' } },
                    label = { Text("Amount ($currencySymbol)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    label = { Text("Reason (optional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (amount != null && amount > 0) {
                        onConfirm(amount, isAddition, noteText.takeIf { it.isNotBlank() })
                    }
                },
                enabled = amount != null && amount > 0,
                colors = if (isAddition) {
                    ButtonDefaults.buttonColors(containerColor = IncomeGreen)
                } else {
                    ButtonDefaults.buttonColors()
                }
            ) {
                Text(if (isAddition) "Add ➕" else "Subtract ➖")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
