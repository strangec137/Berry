package com.myapp.buckwheat.ui.settings;

import com.myapp.buckwheat.data.repository.PeriodRepository;
import com.myapp.buckwheat.data.repository.SettingsRepository;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import com.myapp.buckwheat.domain.usecase.ExportCsvUseCase;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<PeriodRepository> periodRepositoryProvider;

  private final Provider<ExportCsvUseCase> exportCsvUseCaseProvider;

  public SettingsViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<PeriodRepository> periodRepositoryProvider,
      Provider<ExportCsvUseCase> exportCsvUseCaseProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.periodRepositoryProvider = periodRepositoryProvider;
    this.exportCsvUseCaseProvider = exportCsvUseCaseProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), transactionRepositoryProvider.get(), periodRepositoryProvider.get(), exportCsvUseCaseProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<PeriodRepository> periodRepositoryProvider,
      Provider<ExportCsvUseCase> exportCsvUseCaseProvider) {
    return new SettingsViewModel_Factory(settingsRepositoryProvider, transactionRepositoryProvider, periodRepositoryProvider, exportCsvUseCaseProvider);
  }

  public static SettingsViewModel newInstance(SettingsRepository settingsRepository,
      TransactionRepository transactionRepository, PeriodRepository periodRepository,
      ExportCsvUseCase exportCsvUseCase) {
    return new SettingsViewModel(settingsRepository, transactionRepository, periodRepository, exportCsvUseCase);
  }
}
