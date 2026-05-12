package com.myapp.buckwheat.ui.analytics;

import androidx.lifecycle.ViewModel;
import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import com.myapp.buckwheat.domain.model.AnalyticsData;
import com.myapp.buckwheat.domain.model.SpendRecord;
import com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0082@\u00a2\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0016R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/myapp/buckwheat/ui/analytics/AnalyticsViewModel;", "Landroidx/lifecycle/ViewModel;", "transactionRepository", "Lcom/myapp/buckwheat/data/repository/TransactionRepository;", "settingsRepository", "Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "calculateDailyBudgetUseCase", "Lcom/myapp/buckwheat/domain/usecase/CalculateDailyBudgetUseCase;", "(Lcom/myapp/buckwheat/data/repository/TransactionRepository;Lcom/myapp/buckwheat/data/repository/SettingsRepository;Lcom/myapp/buckwheat/domain/usecase/CalculateDailyBudgetUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/myapp/buckwheat/ui/analytics/AnalyticsUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "computeAnalytics", "", "start", "Ljava/time/LocalDate;", "end", "symbol", "", "(Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadAnalytics", "selectPeriod", "period", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AnalyticsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase calculateDailyBudgetUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.myapp.buckwheat.ui.analytics.AnalyticsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.myapp.buckwheat.ui.analytics.AnalyticsUiState> uiState = null;
    
    @javax.inject.Inject()
    public AnalyticsViewModel(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase calculateDailyBudgetUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.myapp.buckwheat.ui.analytics.AnalyticsUiState> getUiState() {
        return null;
    }
    
    private final void loadAnalytics() {
    }
    
    public final void selectPeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String period) {
    }
    
    private final java.lang.Object computeAnalytics(java.time.LocalDate start, java.time.LocalDate end, java.lang.String symbol, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}