package com.myapp.buckwheat.ui.main;

import com.myapp.buckwheat.data.repository.PeriodRepository;
import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import com.myapp.buckwheat.domain.usecase.CalculateBalanceUseCase;
import com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase;
import com.myapp.buckwheat.domain.usecase.FormatAmountUseCase;
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<PeriodRepository> periodRepositoryProvider;

  private final Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider;

  private final Provider<CalculateBalanceUseCase> calculateBalanceUseCaseProvider;

  private final Provider<FormatAmountUseCase> formatAmountUseCaseProvider;

  public MainViewModel_Factory(Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<PeriodRepository> periodRepositoryProvider,
      Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider,
      Provider<CalculateBalanceUseCase> calculateBalanceUseCaseProvider,
      Provider<FormatAmountUseCase> formatAmountUseCaseProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.periodRepositoryProvider = periodRepositoryProvider;
    this.calculateDailyBudgetUseCaseProvider = calculateDailyBudgetUseCaseProvider;
    this.calculateBalanceUseCaseProvider = calculateBalanceUseCaseProvider;
    this.formatAmountUseCaseProvider = formatAmountUseCaseProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(transactionRepositoryProvider.get(), settingsRepositoryProvider.get(), periodRepositoryProvider.get(), calculateDailyBudgetUseCaseProvider.get(), calculateBalanceUseCaseProvider.get(), formatAmountUseCaseProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<PeriodRepository> periodRepositoryProvider,
      Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider,
      Provider<CalculateBalanceUseCase> calculateBalanceUseCaseProvider,
      Provider<FormatAmountUseCase> formatAmountUseCaseProvider) {
    return new MainViewModel_Factory(transactionRepositoryProvider, settingsRepositoryProvider, periodRepositoryProvider, calculateDailyBudgetUseCaseProvider, calculateBalanceUseCaseProvider, formatAmountUseCaseProvider);
  }

  public static MainViewModel newInstance(TransactionRepository transactionRepository,
      SettingsRepository settingsRepository, PeriodRepository periodRepository,
      CalculateDailyBudgetUseCase calculateDailyBudgetUseCase,
      CalculateBalanceUseCase calculateBalanceUseCase, FormatAmountUseCase formatAmountUseCase) {
    return new MainViewModel(transactionRepository, settingsRepository, periodRepository, calculateDailyBudgetUseCase, calculateBalanceUseCase, formatAmountUseCase);
  }
}
