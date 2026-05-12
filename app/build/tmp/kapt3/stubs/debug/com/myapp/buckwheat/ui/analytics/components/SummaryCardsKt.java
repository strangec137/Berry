package com.myapp.buckwheat.ui.analytics.components;

import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.myapp.buckwheat.domain.model.AnalyticsData;
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase;
import java.time.format.DateTimeFormatter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0003\u001a*\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u0012"}, d2 = {"SummaryCard", "", "label", "", "value", "visible", "", "delayMs", "", "modifier", "Landroidx/compose/ui/Modifier;", "subtitle", "SummaryCards", "analyticsData", "Lcom/myapp/buckwheat/domain/model/AnalyticsData;", "currencySymbol", "formatAmount", "Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;", "app_debug"})
public final class SummaryCardsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SummaryCards(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.model.AnalyticsData analyticsData, @org.jetbrains.annotations.NotNull()
    java.lang.String currencySymbol, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.FormatAmountUseCase formatAmount, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SummaryCard(java.lang.String label, java.lang.String value, boolean visible, int delayMs, androidx.compose.ui.Modifier modifier, java.lang.String subtitle) {
    }
}