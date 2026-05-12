package com.myapp.buckwheat.ui.settings;

import androidx.lifecycle.ViewModel;
import android.content.Intent;
import com.myapp.buckwheat.data.repository.PeriodRepository;
import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import com.myapp.buckwheat.domain.usecase.ExportCsvUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018J\u0006\u0010\u0019\u001a\u00020\u0016J\u001a\u0010\u001a\u001a\u00020\u00162\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00160\u001cJ\u0016\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0010J\u000e\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u0010J\u000e\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u0010J\u0016\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006."}, d2 = {"Lcom/myapp/buckwheat/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "transactionRepository", "Lcom/myapp/buckwheat/data/repository/TransactionRepository;", "periodRepository", "Lcom/myapp/buckwheat/data/repository/PeriodRepository;", "exportCsvUseCase", "Lcom/myapp/buckwheat/domain/usecase/ExportCsvUseCase;", "(Lcom/myapp/buckwheat/data/repository/SettingsRepository;Lcom/myapp/buckwheat/data/repository/TransactionRepository;Lcom/myapp/buckwheat/data/repository/PeriodRepository;Lcom/myapp/buckwheat/domain/usecase/ExportCsvUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/myapp/buckwheat/ui/settings/SettingsUiState;", "themeMode", "Lkotlinx/coroutines/flow/StateFlow;", "", "getThemeMode", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "clearAllData", "", "onDone", "Lkotlin/Function0;", "completeOnboarding", "exportCsv", "onResult", "Lkotlin/Function1;", "Landroid/content/Intent;", "setCurrency", "code", "symbol", "setHideOverspend", "hide", "", "setInitialBudget", "amount", "", "setResidueMethod", "method", "setThemeMode", "mode", "setupPeriod", "startDate", "endDate", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.PeriodRepository periodRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.ExportCsvUseCase exportCsvUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.myapp.buckwheat.ui.settings.SettingsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.myapp.buckwheat.ui.settings.SettingsUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> themeMode = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.PeriodRepository periodRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.ExportCsvUseCase exportCsvUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.myapp.buckwheat.ui.settings.SettingsUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getThemeMode() {
        return null;
    }
    
    public final void setThemeMode(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    public final void setResidueMethod(@org.jetbrains.annotations.NotNull()
    java.lang.String method) {
    }
    
    public final void setHideOverspend(boolean hide) {
    }
    
    public final void setCurrency(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String symbol) {
    }
    
    public final void exportCsv(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.content.Intent, kotlin.Unit> onResult) {
    }
    
    public final void setupPeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate) {
    }
    
    public final void completeOnboarding() {
    }
    
    public final void setInitialBudget(double amount) {
    }
    
    public final void clearAllData(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
}