package com.myapp.buckwheat.ui.main;

import androidx.compose.animation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.style.TextAlign;
import com.myapp.buckwheat.R;
import com.myapp.buckwheat.ui.main.components.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032 \u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a>\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001aH\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u00a8\u0006\u0019"}, d2 = {"BudgetAdjustmentDialog", "", "currencySymbol", "", "onConfirm", "Lkotlin/Function3;", "", "", "onDismiss", "Lkotlin/Function0;", "DailyBudgetPanel", "dailyBudget", "daysLeft", "", "todaySpent", "formatAmount", "Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;", "onClick", "MainScreen", "viewModel", "Lcom/myapp/buckwheat/ui/main/MainViewModel;", "onNavigateToAnalytics", "onNavigateToSettings", "onPeriodEnded", "onNavigateToHistory", "app_debug"})
public final class MainScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void MainScreen(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.ui.main.MainViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToAnalytics, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToSettings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPeriodEnded, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToHistory) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DailyBudgetPanel(double dailyBudget, int daysLeft, double todaySpent, java.lang.String currencySymbol, com.myapp.buckwheat.domain.usecase.FormatAmountUseCase formatAmount, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BudgetAdjustmentDialog(java.lang.String currencySymbol, kotlin.jvm.functions.Function3<? super java.lang.Double, ? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onConfirm, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}