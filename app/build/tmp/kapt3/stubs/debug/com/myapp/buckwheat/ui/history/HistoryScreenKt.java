package com.myapp.buckwheat.ui.history;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.myapp.buckwheat.domain.model.Transaction;
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0003\u001a \u0010\r\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u00a8\u0006\u0012"}, d2 = {"DateHeader", "", "date", "Ljava/time/LocalDate;", "transactions", "", "Lcom/myapp/buckwheat/domain/model/Transaction;", "currencySymbol", "", "formatAmount", "Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;", "dateFormatter", "Ljava/time/format/DateTimeFormatter;", "HistoryScreen", "onNavigateBack", "Lkotlin/Function0;", "viewModel", "Lcom/myapp/buckwheat/ui/history/HistoryViewModel;", "app_debug"})
public final class HistoryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HistoryScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.ui.history.HistoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DateHeader(java.time.LocalDate date, java.util.List<com.myapp.buckwheat.domain.model.Transaction> transactions, java.lang.String currencySymbol, com.myapp.buckwheat.domain.usecase.FormatAmountUseCase formatAmount, java.time.format.DateTimeFormatter dateFormatter) {
    }
}