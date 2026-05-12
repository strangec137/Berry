package com.myapp.buckwheat.ui.main;

import androidx.lifecycle.ViewModel;
import com.myapp.buckwheat.data.local.entity.TransactionEntity;
import com.myapp.buckwheat.data.repository.PeriodRepository;
import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import com.myapp.buckwheat.data.local.entity.PeriodEntity;
import com.myapp.buckwheat.domain.model.Transaction;
import com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase;
import com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase;
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001a\b\u0007\u0018\u00002\u00020\u0001:\u0001GB7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ \u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%J\u0006\u0010&\u001a\u00020\u0011J(\u0010\'\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020*2\b\u0010$\u001a\u0004\u0018\u00010%J\u0006\u0010+\u001a\u00020\u0011J\u000e\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020\u0011J\u0006\u00100\u001a\u00020\u0011J\u0006\u00101\u001a\u00020\u0011J\u0016\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020#J\b\u00105\u001a\u00020\u0011H\u0002J\b\u00106\u001a\u00020\u0011H\u0002J \u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020%H\u0002J\u0006\u0010;\u001a\u00020\u0011J\u0006\u0010<\u001a\u00020\u0011J\u000e\u0010=\u001a\u00020\u00112\u0006\u0010>\u001a\u00020%J\u000e\u0010?\u001a\u00020\u00112\u0006\u0010@\u001a\u00020%J\u0006\u0010A\u001a\u00020\u0011J\u0006\u0010B\u001a\u00020\u0011J\u000e\u0010C\u001a\u00020\u00112\u0006\u0010D\u001a\u00020#J\u0006\u0010E\u001a\u00020\u0011J\u0016\u0010F\u001a\u00020\u00112\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020*R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006H"}, d2 = {"Lcom/myapp/buckwheat/ui/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "transactionRepository", "Lcom/myapp/buckwheat/data/repository/TransactionRepository;", "settingsRepository", "Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "periodRepository", "Lcom/myapp/buckwheat/data/repository/PeriodRepository;", "calculateDailyBudgetUseCase", "Lcom/myapp/buckwheat/domain/usecase/CalculateDailyBudgetUseCase;", "calculateBalanceUseCase", "Lcom/myapp/buckwheat/domain/usecase/CalculateBalanceUseCase;", "formatAmountUseCase", "Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;", "(Lcom/myapp/buckwheat/data/repository/TransactionRepository;Lcom/myapp/buckwheat/data/repository/SettingsRepository;Lcom/myapp/buckwheat/data/repository/PeriodRepository;Lcom/myapp/buckwheat/domain/usecase/CalculateDailyBudgetUseCase;Lcom/myapp/buckwheat/domain/usecase/CalculateBalanceUseCase;Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;)V", "_sanjiEvent", "Lkotlinx/coroutines/channels/Channel;", "", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/myapp/buckwheat/ui/main/MainUiState;", "getFormatAmountUseCase", "()Lcom/myapp/buckwheat/domain/usecase/FormatAmountUseCase;", "sanjiEvent", "Lkotlinx/coroutines/flow/Flow;", "getSanjiEvent", "()Lkotlinx/coroutines/flow/Flow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addBudgetAdjustment", "amount", "", "isAddition", "", "note", "", "addExpense", "addIncome", "source", "date", "Ljava/time/LocalDate;", "checkDayChange", "deleteTransaction", "id", "", "dismissDevilFruitUnlock", "dismissResidueDialog", "finishPeriodEarly", "handleResidue", "addToToday", "remember", "observeSettings", "observeTags", "observeTransactions", "start", "end", "symbol", "onBackspace", "onClear", "onNumberInput", "digit", "onTagChanged", "tag", "toggleAdjustDialog", "toggleEditPeriod", "toggleIncomeSheet", "show", "toggleWalletSheet", "updatePeriod", "SettingsSnapshot", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.PeriodRepository periodRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase calculateDailyBudgetUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase calculateBalanceUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.FormatAmountUseCase formatAmountUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.myapp.buckwheat.ui.main.MainUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.myapp.buckwheat.ui.main.MainUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<kotlin.Unit> _sanjiEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<kotlin.Unit> sanjiEvent = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.PeriodRepository periodRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase calculateDailyBudgetUseCase, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase calculateBalanceUseCase, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.FormatAmountUseCase formatAmountUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.domain.usecase.FormatAmountUseCase getFormatAmountUseCase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.myapp.buckwheat.ui.main.MainUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Unit> getSanjiEvent() {
        return null;
    }
    
    private final void observeSettings() {
    }
    
    private final void observeTransactions(java.time.LocalDate start, java.time.LocalDate end, java.lang.String symbol) {
    }
    
    private final void observeTags() {
    }
    
    public final void onNumberInput(@org.jetbrains.annotations.NotNull()
    java.lang.String digit) {
    }
    
    public final void onBackspace() {
    }
    
    public final void onClear() {
    }
    
    public final void onTagChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
    }
    
    public final void addExpense() {
    }
    
    public final void addBudgetAdjustment(double amount, boolean isAddition, @org.jetbrains.annotations.Nullable()
    java.lang.String note) {
    }
    
    public final void toggleAdjustDialog() {
    }
    
    public final void addIncome(double amount, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.Nullable()
    java.lang.String note) {
    }
    
    public final void dismissDevilFruitUnlock() {
    }
    
    public final void deleteTransaction(long id) {
    }
    
    public final void updatePeriod(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate start, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate end) {
    }
    
    public final void toggleEditPeriod() {
    }
    
    public final void toggleWalletSheet() {
    }
    
    public final void toggleIncomeSheet(boolean show) {
    }
    
    public final void dismissResidueDialog() {
    }
    
    public final void handleResidue(boolean addToToday, boolean remember) {
    }
    
    public final void finishPeriodEarly() {
    }
    
    public final void checkDayChange() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e\u00a8\u0006%"}, d2 = {"Lcom/myapp/buckwheat/ui/main/MainViewModel$SettingsSnapshot;", "", "periodStart", "", "periodEnd", "symbol", "code", "onboardingDone", "", "theme", "hideOverspend", "residue", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getHideOverspend", "()Z", "getOnboardingDone", "getPeriodEnd", "getPeriodStart", "getResidue", "getSymbol", "getTheme", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    static final class SettingsSnapshot {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String periodStart = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String periodEnd = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String symbol = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String code = null;
        private final boolean onboardingDone = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String theme = null;
        private final boolean hideOverspend = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String residue = null;
        
        public SettingsSnapshot(@org.jetbrains.annotations.NotNull()
        java.lang.String periodStart, @org.jetbrains.annotations.NotNull()
        java.lang.String periodEnd, @org.jetbrains.annotations.NotNull()
        java.lang.String symbol, @org.jetbrains.annotations.NotNull()
        java.lang.String code, boolean onboardingDone, @org.jetbrains.annotations.NotNull()
        java.lang.String theme, boolean hideOverspend, @org.jetbrains.annotations.NotNull()
        java.lang.String residue) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPeriodStart() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPeriodEnd() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSymbol() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCode() {
            return null;
        }
        
        public final boolean getOnboardingDone() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTheme() {
            return null;
        }
        
        public final boolean getHideOverspend() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getResidue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        public final boolean component7() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.myapp.buckwheat.ui.main.MainViewModel.SettingsSnapshot copy(@org.jetbrains.annotations.NotNull()
        java.lang.String periodStart, @org.jetbrains.annotations.NotNull()
        java.lang.String periodEnd, @org.jetbrains.annotations.NotNull()
        java.lang.String symbol, @org.jetbrains.annotations.NotNull()
        java.lang.String code, boolean onboardingDone, @org.jetbrains.annotations.NotNull()
        java.lang.String theme, boolean hideOverspend, @org.jetbrains.annotations.NotNull()
        java.lang.String residue) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}