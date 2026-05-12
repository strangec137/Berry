package com.myapp.buckwheat.ui.main.components;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase;
import com.myapp.buckwheat.ui.main.MainUiState;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0003\u001a\u0080\u0001\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007\u00a8\u0006\u0017"}, d2 = {"StatItem", "", "label", "", "value", "modifier", "Landroidx/compose/ui/Modifier;", "WalletBottomSheet", "uiState", "Lcom/myapp/buckwheat/ui/main/MainUiState;", "formatAmount", "Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;", "onDismiss", "Lkotlin/Function0;", "onEditPeriod", "onApplyPeriod", "Lkotlin/Function2;", "Ljava/time/LocalDate;", "onCancelEdit", "onAddIncome", "onFinishEarly", "snackbarHostState", "Landroidx/compose/material3/SnackbarHostState;", "app_debug"})
public final class WalletBottomSheetKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void WalletBottomSheet(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.ui.main.MainUiState uiState, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.FormatAmountUseCase formatAmount, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEditPeriod, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.time.LocalDate, ? super java.time.LocalDate, kotlin.Unit> onApplyPeriod, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddIncome, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFinishEarly, @org.jetbrains.annotations.NotNull()
    androidx.compose.material3.SnackbarHostState snackbarHostState) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatItem(java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
}