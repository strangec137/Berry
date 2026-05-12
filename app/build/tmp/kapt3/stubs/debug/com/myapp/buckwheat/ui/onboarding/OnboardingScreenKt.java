package com.myapp.buckwheat.ui.onboarding;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.myapp.buckwheat.ui.settings.SettingsViewModel;
import coil.decode.GifDecoder;
import coil.request.ImageRequest;
import com.myapp.buckwheat.R;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a \u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\b\u0010\u0007\u001a\u00020\u0001H\u0003\u001aX\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013H\u0003\u001a$\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u0013H\u0003\u001a\b\u0010\u0019\u001a\u00020\u0001H\u0003\u00a8\u0006\u001a"}, d2 = {"IncomeInfoPage", "", "OnboardingScreen", "onComplete", "Lkotlin/Function0;", "viewModel", "Lcom/myapp/buckwheat/ui/settings/SettingsViewModel;", "PeriodInfoPage", "SetupPage", "startDate", "Ljava/time/LocalDate;", "endDate", "selectedCurrency", "Lcom/myapp/buckwheat/ui/settings/CurrencyInfo;", "dateFormatter", "Ljava/time/format/DateTimeFormatter;", "onStartDateClick", "onEndDateClick", "onCurrencyChange", "Lkotlin/Function1;", "", "StartingBudgetPage", "amountStr", "", "onAmountChange", "WelcomePage", "app_debug"})
public final class OnboardingScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void OnboardingScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.ui.settings.SettingsViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WelcomePage() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PeriodInfoPage() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void IncomeInfoPage() {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void SetupPage(java.time.LocalDate startDate, java.time.LocalDate endDate, com.myapp.buckwheat.ui.settings.CurrencyInfo selectedCurrency, java.time.format.DateTimeFormatter dateFormatter, kotlin.jvm.functions.Function0<kotlin.Unit> onStartDateClick, kotlin.jvm.functions.Function0<kotlin.Unit> onEndDateClick, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onCurrencyChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StartingBudgetPage(java.lang.String amountStr, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAmountChange) {
    }
}