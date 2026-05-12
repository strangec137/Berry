package com.myapp.buckwheat.domain.usecase;

import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DetectDayChangeUseCase_Factory implements Factory<DetectDayChangeUseCase> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider;

  private final Provider<CalculateBalanceUseCase> calculateBalanceUseCaseProvider;

  public DetectDayChangeUseCase_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider,
      Provider<CalculateBalanceUseCase> calculateBalanceUseCaseProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.calculateDailyBudgetUseCaseProvider = calculateDailyBudgetUseCaseProvider;
    this.calculateBalanceUseCaseProvider = calculateBalanceUseCaseProvider;
  }

  @Override
  public DetectDayChangeUseCase get() {
    return newInstance(settingsRepositoryProvider.get(), transactionRepositoryProvider.get(), calculateDailyBudgetUseCaseProvider.get(), calculateBalanceUseCaseProvider.get());
  }

  public static DetectDayChangeUseCase_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider,
      Provider<CalculateBalanceUseCase> calculateBalanceUseCaseProvider) {
    return new DetectDayChangeUseCase_Factory(settingsRepositoryProvider, transactionRepositoryProvider, calculateDailyBudgetUseCaseProvider, calculateBalanceUseCaseProvider);
  }

  public static DetectDayChangeUseCase newInstance(SettingsRepository settingsRepository,
      TransactionRepository transactionRepository,
      CalculateDailyBudgetUseCase calculateDailyBudgetUseCase,
      CalculateBalanceUseCase calculateBalanceUseCase) {
    return new DetectDayChangeUseCase(settingsRepository, transactionRepository, calculateDailyBudgetUseCase, calculateBalanceUseCase);
  }
}
