package com.myapp.buckwheat.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.buckwheat.ui.settings.SettingsViewModel
import com.myapp.buckwheat.ui.settings.worldCurrencies
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.myapp.buckwheat.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onComplete: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    val scope = rememberCoroutineScope()

    var startDate by remember { mutableStateOf(LocalDate.now()) }
    var endDate by remember { mutableStateOf(LocalDate.now().plusMonths(1)) }
    var selectedCurrencyIndex by remember { mutableStateOf(worldCurrencies.indexOfFirst { it.code == "BDT" }) }
    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }
    var startingBudgetStr by remember { mutableStateOf("") }

    val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when (page) {
                        0 -> WelcomePage()
                        1 -> PeriodInfoPage()
                        2 -> IncomeInfoPage()
                        3 -> StartingBudgetPage(
                            amountStr = startingBudgetStr,
                            onAmountChange = { startingBudgetStr = it }
                        )
                        4 -> SetupPage(
                            startDate = startDate,
                            endDate = endDate,
                            selectedCurrency = if (selectedCurrencyIndex >= 0)
                                worldCurrencies[selectedCurrencyIndex] else worldCurrencies[6],
                            dateFormatter = dateFormatter,
                            onStartDateClick = { showStartPicker = true },
                            onEndDateClick = { showEndPicker = true },
                            onCurrencyChange = { selectedCurrencyIndex = it }
                        )
                    }
                }
            }

            // Page indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    val selected = pagerState.currentPage == index
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (selected) 10.dp else 8.dp),
                        shape = MaterialTheme.shapes.extraSmall,
                        color = if (selected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outlineVariant
                    ) {}
                }
            }

            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (pagerState.currentPage > 0) {
                    TextButton(
                        onClick = {
                            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                        }
                    ) {
                        Text("Back")
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                if (pagerState.currentPage < 4) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (pagerState.currentPage == 3) {
                            TextButton(
                                onClick = {
                                    startingBudgetStr = ""
                                    scope.launch { pagerState.animateScrollToPage(4) }
                                }
                            ) {
                                Text("Skip")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Button(
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                            }
                        ) {
                            Text("Next")
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            scope.launch {
                                val currency = if (selectedCurrencyIndex >= 0)
                                    worldCurrencies[selectedCurrencyIndex] else worldCurrencies[6]
                                viewModel.setCurrency(currency.code, currency.symbol)
                                viewModel.setupPeriod(
                                    startDate.toString(),
                                    endDate.toString()
                                )
                                val initialAmt = startingBudgetStr.toDoubleOrNull() ?: 0.0
                                if (initialAmt > 0) {
                                    viewModel.setInitialBudget(initialAmt)
                                }
                                viewModel.completeOnboarding()
                                onComplete()
                            }
                        },
                        enabled = startDate.isBefore(endDate)
                    ) {
                        Text("Start Budgeting")
                    }
                }
            }
        }
    }

    // Date pickers
    if (showStartPicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = startDate.toEpochDay() * 86400000L
        )
        DatePickerDialog(
            onDismissRequest = { showStartPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        startDate = LocalDate.ofEpochDay(millis / 86400000L)
                    }
                    showStartPicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showStartPicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showEndPicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = endDate.toEpochDay() * 86400000L
        )
        DatePickerDialog(
            onDismissRequest = { showEndPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        endDate = LocalDate.ofEpochDay(millis / 86400000L)
                    }
                    showEndPicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showEndPicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
private fun WelcomePage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components { add(GifDecoder.Factory()) }
            .build()

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.luffy_welcome)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Luffy",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Welcome to Berry",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Let's save money together",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PeriodInfoPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.CalendarMonth,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Set a period",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Choose your budget period — like your pay cycle. The app will track your spending within this timeframe.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun IncomeInfoPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.AddCard,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Add income as you receive it",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Any time, any amount. Your daily budget updates automatically. No need to enter your full salary upfront.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetupPage(
    startDate: LocalDate,
    endDate: LocalDate,
    selectedCurrency: com.myapp.buckwheat.ui.settings.CurrencyInfo,
    dateFormatter: DateTimeFormatter,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    onCurrencyChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Set up your budget",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Start date
        OutlinedButton(
            onClick = onStartDateClick,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Start: ${startDate.format(dateFormatter)}")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // End date
        OutlinedButton(
            onClick = onEndDateClick,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("End: ${endDate.format(dateFormatter)}")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Currency selector
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = "${selectedCurrency.symbol} (${selectedCurrency.code})",
                onValueChange = {},
                readOnly = true,
                label = { Text("Currency") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = MaterialTheme.shapes.medium
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                com.myapp.buckwheat.ui.settings.worldCurrencies.forEachIndexed { index, currency ->
                    DropdownMenuItem(
                        text = { Text("${currency.symbol} - ${currency.name} (${currency.code})") },
                        onClick = {
                            onCurrencyChange(index)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun StartingBudgetPage(
    amountStr: String,
    onAmountChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Set Your Starting Budget 🏴\u200D☠\uFE0F",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "How much Berry ฿ do you have to spend?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = if (amountStr.isEmpty()) "0" else amountStr,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        com.myapp.buckwheat.ui.main.components.NumberPad(
            onNumberClick = { key ->
                if (amountStr.length < 10) {
                    if (key == "." && amountStr.contains(".")) return@NumberPad
                    onAmountChange(amountStr + key)
                }
            },
            onBackspace = {
                if (amountStr.isNotEmpty()) {
                    onAmountChange(amountStr.dropLast(1))
                }
            },
            onClear = {
                onAmountChange("")
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
    }
}
