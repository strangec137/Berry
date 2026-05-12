package com.myapp.buckwheat.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyPickerScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredCurrencies = remember(searchQuery) {
        if (searchQuery.isBlank()) worldCurrencies
        else worldCurrencies.filter {
            it.code.contains(searchQuery, ignoreCase = true) ||
                    it.name.contains(searchQuery, ignoreCase = true) ||
                    it.symbol.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Currency") },
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
            // Search
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search currencies...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = MaterialTheme.shapes.medium
            )

            // Custom currency option
            ListItem(
                headlineContent = { Text("Custom", fontWeight = FontWeight.Medium) },
                supportingContent = { Text("Enter your own currency symbol") },
                modifier = Modifier.clickable {
                    viewModel.setCurrency("CUSTOM", "¤")
                    onNavigateBack()
                }
            )

            ListItem(
                headlineContent = { Text("None", fontWeight = FontWeight.Medium) },
                supportingContent = { Text("No currency symbol") },
                modifier = Modifier.clickable {
                    viewModel.setCurrency("NONE", "")
                    onNavigateBack()
                }
            )

            HorizontalDivider()

            // Currency list
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredCurrencies) { currency ->
                    val isSelected = currency.code == uiState.currencyCode

                    ListItem(
                        headlineContent = {
                            Text(
                                "${currency.name} (${currency.code})",
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        supportingContent = { Text(currency.symbol) },
                        trailingContent = {
                            if (isSelected) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        modifier = Modifier.clickable {
                            viewModel.setCurrency(currency.code, currency.symbol)
                            onNavigateBack()
                        }
                    )
                }
            }
        }
    }
}
