package com.myapp.buckwheat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapp.buckwheat.ui.analytics.AnalyticsScreen
import com.myapp.buckwheat.ui.main.MainScreen
import com.myapp.buckwheat.ui.main.MainViewModel
import com.myapp.buckwheat.ui.main.TransactionHistoryScreen
import com.myapp.buckwheat.ui.onboarding.OnboardingScreen
import com.myapp.buckwheat.ui.settings.CurrencyPickerScreen
import com.myapp.buckwheat.ui.settings.SettingsScreen
import com.myapp.buckwheat.ui.summary.PeriodSummaryScreen

object Routes {
    const val ONBOARDING = "onboarding"
    const val MAIN = "main"
    const val ANALYTICS = "analytics"
    const val SETTINGS = "settings"
    const val CURRENCY_PICKER = "currency_picker"
    const val PERIOD_SUMMARY = "period_summary"
    const val TRANSACTION_HISTORY = "transaction_history"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val uiState by mainViewModel.uiState.collectAsState()

    val startDestination = when {
        uiState.isLoading -> Routes.MAIN // will show loading
        !uiState.onboardingDone -> Routes.ONBOARDING
        uiState.isPeriodEnded -> Routes.PERIOD_SUMMARY
        else -> Routes.MAIN
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onComplete = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.MAIN) {
            MainScreen(
                viewModel = mainViewModel,
                onNavigateToAnalytics = { navController.navigate(Routes.ANALYTICS) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) },
                onPeriodEnded = {
                    navController.navigate(Routes.PERIOD_SUMMARY) {
                        popUpTo(Routes.MAIN) { inclusive = true }
                    }
                },
                onNavigateToHistory = { navController.navigate(Routes.TRANSACTION_HISTORY) }
            )
        }

        composable(Routes.TRANSACTION_HISTORY) {
            TransactionHistoryScreen(
                viewModel = mainViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ANALYTICS) {
            AnalyticsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCurrencyPicker = { navController.navigate(Routes.CURRENCY_PICKER) }
            )
        }

        composable(Routes.CURRENCY_PICKER) {
            CurrencyPickerScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PERIOD_SUMMARY) {
            PeriodSummaryScreen(
                onSetupNewPeriod = {
                    navController.navigate(Routes.ONBOARDING) {
                        popUpTo(Routes.PERIOD_SUMMARY) { inclusive = true }
                    }
                }
            )
        }
    }
}
