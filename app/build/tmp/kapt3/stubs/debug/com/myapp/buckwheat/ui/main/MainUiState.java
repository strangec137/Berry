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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\bI\b\u0086\b\u0018\u00002\u00020\u0001B\u00d3\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001b\u001a\u00020\r\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001d\u001a\u00020\n\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001f\u001a\u00020\n\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\n\u0012\b\b\u0002\u0010$\u001a\u00020\n\u0012\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\u0016\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\'\u0012\b\b\u0002\u0010(\u001a\u00020\u0003\u00a2\u0006\u0002\u0010)J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\rH\u00c6\u0003J\t\u0010N\u001a\u00020\rH\u00c6\u0003J\t\u0010O\u001a\u00020\rH\u00c6\u0003J\t\u0010P\u001a\u00020\u0013H\u00c6\u0003J\t\u0010Q\u001a\u00020\rH\u00c6\u0003J\u000f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u00c6\u0003J\u000f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u00c6\u0003J\t\u0010T\u001a\u00020\u0003H\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\rH\u00c6\u0003J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Y\u001a\u00020\nH\u00c6\u0003J\t\u0010Z\u001a\u00020\u0003H\u00c6\u0003J\t\u0010[\u001a\u00020\nH\u00c6\u0003J\t\u0010\\\u001a\u00020\u0003H\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020\u0003H\u00c6\u0003J\t\u0010_\u001a\u00020\nH\u00c6\u0003J\t\u0010`\u001a\u00020\nH\u00c6\u0003J\u000f\u0010a\u001a\b\u0012\u0004\u0012\u00020\n0\u0016H\u00c6\u0003J\t\u0010b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\'H\u00c6\u0003J\t\u0010d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010g\u001a\u00020\nH\u00c6\u0003J\t\u0010h\u001a\u00020\nH\u00c6\u0003J\t\u0010i\u001a\u00020\rH\u00c6\u0003J\t\u0010j\u001a\u00020\rH\u00c6\u0003J\u00d7\u0002\u0010k\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\r2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\n2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\n2\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\n2\b\b\u0002\u0010$\u001a\u00020\n2\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\u00162\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\'2\b\b\u0002\u0010(\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010l\u001a\u00020\u00032\b\u0010m\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010n\u001a\u00020\u0013H\u00d6\u0001J\t\u0010o\u001a\u00020\nH\u00d6\u0001R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0014\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010/R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010-R\u0011\u0010\u0010\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010-R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010#\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010/R\u0011\u0010$\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010/R\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u00107R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u00107R\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u00107R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u00107R\u0013\u0010&\u001a\u0004\u0018\u00010\'\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u00107R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010>R\u0011\u0010\u001f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010/R\u0011\u0010\u001b\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010-R\u0011\u0010\"\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u00107R\u0011\u0010(\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u00107R\u0011\u0010!\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u00107R\u0011\u0010\u001a\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u00107R\u0011\u0010 \u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u00107R\u0011\u0010\u001d\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010/R\u0011\u0010\u0011\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010-R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010+R\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010-R\u0011\u0010\u000f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010-\u00a8\u0006p"}, d2 = {"Lcom/myapp/buckwheat/ui/main/MainUiState;", "", "isLoading", "", "onboardingDone", "isPeriodSet", "periodStart", "Ljava/time/LocalDate;", "periodEnd", "currencySymbol", "", "currencyCode", "currentBalance", "", "totalIncome", "totalSpent", "dailyBudget", "todaySpent", "daysLeft", "", "averageSpend", "todayTransactions", "", "Lcom/myapp/buckwheat/domain/model/Transaction;", "allPeriodTransactions", "isPeriodEnded", "showResidueDialog", "residueSurplus", "isEditingPeriod", "themeMode", "hideOverspendWarn", "residueMethod", "showWalletSheet", "showIncomeSheet", "showAdjustDialog", "inputAmount", "inputTag", "distinctTags", "lastIncome", "Lcom/myapp/buckwheat/data/local/entity/TransactionEntity;", "showDevilFruitUnlock", "(ZZZLjava/time/LocalDate;Ljava/time/LocalDate;Ljava/lang/String;Ljava/lang/String;DDDDDIDLjava/util/List;Ljava/util/List;ZZDZLjava/lang/String;ZLjava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/myapp/buckwheat/data/local/entity/TransactionEntity;Z)V", "getAllPeriodTransactions", "()Ljava/util/List;", "getAverageSpend", "()D", "getCurrencyCode", "()Ljava/lang/String;", "getCurrencySymbol", "getCurrentBalance", "getDailyBudget", "getDaysLeft", "()I", "getDistinctTags", "getHideOverspendWarn", "()Z", "getInputAmount", "getInputTag", "getLastIncome", "()Lcom/myapp/buckwheat/data/local/entity/TransactionEntity;", "getOnboardingDone", "getPeriodEnd", "()Ljava/time/LocalDate;", "getPeriodStart", "getResidueMethod", "getResidueSurplus", "getShowAdjustDialog", "getShowDevilFruitUnlock", "getShowIncomeSheet", "getShowResidueDialog", "getShowWalletSheet", "getThemeMode", "getTodaySpent", "getTodayTransactions", "getTotalIncome", "getTotalSpent", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class MainUiState {
    private final boolean isLoading = false;
    private final boolean onboardingDone = false;
    private final boolean isPeriodSet = false;
    @org.jetbrains.annotations.Nullable()
    private final java.time.LocalDate periodStart = null;
    @org.jetbrains.annotations.Nullable()
    private final java.time.LocalDate periodEnd = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currencySymbol = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currencyCode = null;
    private final double currentBalance = 0.0;
    private final double totalIncome = 0.0;
    private final double totalSpent = 0.0;
    private final double dailyBudget = 0.0;
    private final double todaySpent = 0.0;
    private final int daysLeft = 0;
    private final double averageSpend = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.myapp.buckwheat.domain.model.Transaction> todayTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.myapp.buckwheat.domain.model.Transaction> allPeriodTransactions = null;
    private final boolean isPeriodEnded = false;
    private final boolean showResidueDialog = false;
    private final double residueSurplus = 0.0;
    private final boolean isEditingPeriod = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String themeMode = null;
    private final boolean hideOverspendWarn = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String residueMethod = null;
    private final boolean showWalletSheet = false;
    private final boolean showIncomeSheet = false;
    private final boolean showAdjustDialog = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String inputAmount = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String inputTag = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> distinctTags = null;
    @org.jetbrains.annotations.Nullable()
    private final com.myapp.buckwheat.data.local.entity.TransactionEntity lastIncome = null;
    private final boolean showDevilFruitUnlock = false;
    
    public MainUiState(boolean isLoading, boolean onboardingDone, boolean isPeriodSet, @org.jetbrains.annotations.Nullable()
    java.time.LocalDate periodStart, @org.jetbrains.annotations.Nullable()
    java.time.LocalDate periodEnd, @org.jetbrains.annotations.NotNull()
    java.lang.String currencySymbol, @org.jetbrains.annotations.NotNull()
    java.lang.String currencyCode, double currentBalance, double totalIncome, double totalSpent, double dailyBudget, double todaySpent, int daysLeft, double averageSpend, @org.jetbrains.annotations.NotNull()
    java.util.List<com.myapp.buckwheat.domain.model.Transaction> todayTransactions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.myapp.buckwheat.domain.model.Transaction> allPeriodTransactions, boolean isPeriodEnded, boolean showResidueDialog, double residueSurplus, boolean isEditingPeriod, @org.jetbrains.annotations.NotNull()
    java.lang.String themeMode, boolean hideOverspendWarn, @org.jetbrains.annotations.NotNull()
    java.lang.String residueMethod, boolean showWalletSheet, boolean showIncomeSheet, boolean showAdjustDialog, @org.jetbrains.annotations.NotNull()
    java.lang.String inputAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String inputTag, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> distinctTags, @org.jetbrains.annotations.Nullable()
    com.myapp.buckwheat.data.local.entity.TransactionEntity lastIncome, boolean showDevilFruitUnlock) {
        super();
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final boolean getOnboardingDone() {
        return false;
    }
    
    public final boolean isPeriodSet() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate getPeriodStart() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate getPeriodEnd() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrencySymbol() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrencyCode() {
        return null;
    }
    
    public final double getCurrentBalance() {
        return 0.0;
    }
    
    public final double getTotalIncome() {
        return 0.0;
    }
    
    public final double getTotalSpent() {
        return 0.0;
    }
    
    public final double getDailyBudget() {
        return 0.0;
    }
    
    public final double getTodaySpent() {
        return 0.0;
    }
    
    public final int getDaysLeft() {
        return 0;
    }
    
    public final double getAverageSpend() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.myapp.buckwheat.domain.model.Transaction> getTodayTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.myapp.buckwheat.domain.model.Transaction> getAllPeriodTransactions() {
        return null;
    }
    
    public final boolean isPeriodEnded() {
        return false;
    }
    
    public final boolean getShowResidueDialog() {
        return false;
    }
    
    public final double getResidueSurplus() {
        return 0.0;
    }
    
    public final boolean isEditingPeriod() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThemeMode() {
        return null;
    }
    
    public final boolean getHideOverspendWarn() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResidueMethod() {
        return null;
    }
    
    public final boolean getShowWalletSheet() {
        return false;
    }
    
    public final boolean getShowIncomeSheet() {
        return false;
    }
    
    public final boolean getShowAdjustDialog() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInputAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInputTag() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getDistinctTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.myapp.buckwheat.data.local.entity.TransactionEntity getLastIncome() {
        return null;
    }
    
    public final boolean getShowDevilFruitUnlock() {
        return false;
    }
    
    public MainUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    public final double component11() {
        return 0.0;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    public final int component13() {
        return 0;
    }
    
    public final double component14() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.myapp.buckwheat.domain.model.Transaction> component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.myapp.buckwheat.domain.model.Transaction> component16() {
        return null;
    }
    
    public final boolean component17() {
        return false;
    }
    
    public final boolean component18() {
        return false;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component20() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component21() {
        return null;
    }
    
    public final boolean component22() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    public final boolean component24() {
        return false;
    }
    
    public final boolean component25() {
        return false;
    }
    
    public final boolean component26() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component29() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.myapp.buckwheat.data.local.entity.TransactionEntity component30() {
        return null;
    }
    
    public final boolean component31() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.ui.main.MainUiState copy(boolean isLoading, boolean onboardingDone, boolean isPeriodSet, @org.jetbrains.annotations.Nullable()
    java.time.LocalDate periodStart, @org.jetbrains.annotations.Nullable()
    java.time.LocalDate periodEnd, @org.jetbrains.annotations.NotNull()
    java.lang.String currencySymbol, @org.jetbrains.annotations.NotNull()
    java.lang.String currencyCode, double currentBalance, double totalIncome, double totalSpent, double dailyBudget, double todaySpent, int daysLeft, double averageSpend, @org.jetbrains.annotations.NotNull()
    java.util.List<com.myapp.buckwheat.domain.model.Transaction> todayTransactions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.myapp.buckwheat.domain.model.Transaction> allPeriodTransactions, boolean isPeriodEnded, boolean showResidueDialog, double residueSurplus, boolean isEditingPeriod, @org.jetbrains.annotations.NotNull()
    java.lang.String themeMode, boolean hideOverspendWarn, @org.jetbrains.annotations.NotNull()
    java.lang.String residueMethod, boolean showWalletSheet, boolean showIncomeSheet, boolean showAdjustDialog, @org.jetbrains.annotations.NotNull()
    java.lang.String inputAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String inputTag, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> distinctTags, @org.jetbrains.annotations.Nullable()
    com.myapp.buckwheat.data.local.entity.TransactionEntity lastIncome, boolean showDevilFruitUnlock) {
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