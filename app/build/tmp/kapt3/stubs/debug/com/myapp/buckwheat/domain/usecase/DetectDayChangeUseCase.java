package com.myapp.buckwheat.domain.usecase;

import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import java.time.LocalDate;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\fH\u0086B\u00a2\u0006\u0002\u0010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/myapp/buckwheat/domain/usecase/DetectDayChangeUseCase;", "", "settingsRepository", "Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "transactionRepository", "Lcom/myapp/buckwheat/data/repository/TransactionRepository;", "calculateDailyBudgetUseCase", "Lcom/myapp/buckwheat/domain/usecase/CalculateDailyBudgetUseCase;", "calculateBalanceUseCase", "Lcom/myapp/buckwheat/domain/usecase/CalculateBalanceUseCase;", "(Lcom/myapp/buckwheat/data/repository/SettingsRepository;Lcom/myapp/buckwheat/data/repository/TransactionRepository;Lcom/myapp/buckwheat/domain/usecase/CalculateDailyBudgetUseCase;Lcom/myapp/buckwheat/domain/usecase/CalculateBalanceUseCase;)V", "invoke", "Lcom/myapp/buckwheat/domain/usecase/DayChangeResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DetectDayChangeUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase calculateDailyBudgetUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase calculateBalanceUseCase = null;
    
    @javax.inject.Inject()
    public DetectDayChangeUseCase(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.TransactionRepository transactionRepository, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase calculateDailyBudgetUseCase, @org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase calculateBalanceUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.myapp.buckwheat.domain.usecase.DayChangeResult> $completion) {
        return null;
    }
}