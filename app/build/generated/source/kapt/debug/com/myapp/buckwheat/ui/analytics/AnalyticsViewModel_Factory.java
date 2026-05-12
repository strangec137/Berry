package com.myapp.buckwheat.ui.analytics;

import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import com.myapp.buckwheat.domain.usecase.CalculateDailyBudgetUseCase;
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
public final class AnalyticsViewModel_Factory implements Factory<AnalyticsViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider;

  public AnalyticsViewModel_Factory(Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.calculateDailyBudgetUseCaseProvider = calculateDailyBudgetUseCaseProvider;
  }

  @Override
  public AnalyticsViewModel get() {
    return newInstance(transactionRepositoryProvider.get(), settingsRepositoryProvider.get(), calculateDailyBudgetUseCaseProvider.get());
  }

  public static AnalyticsViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<CalculateDailyBudgetUseCase> calculateDailyBudgetUseCaseProvider) {
    return new AnalyticsViewModel_Factory(transactionRepositoryProvider, settingsRepositoryProvider, calculateDailyBudgetUseCaseProvider);
  }

  public static AnalyticsViewModel newInstance(TransactionRepository transactionRepository,
      SettingsRepository settingsRepository,
      CalculateDailyBudgetUseCase calculateDailyBudgetUseCase) {
    return new AnalyticsViewModel(transactionRepository, settingsRepository, calculateDailyBudgetUseCase);
  }
}
