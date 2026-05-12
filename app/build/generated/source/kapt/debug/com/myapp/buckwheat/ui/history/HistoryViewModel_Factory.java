package com.myapp.buckwheat.ui.history;

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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public HistoryViewModel_Factory(Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(transactionRepositoryProvider.get(), settingsRepositoryProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new HistoryViewModel_Factory(transactionRepositoryProvider, settingsRepositoryProvider);
  }

  public static HistoryViewModel newInstance(TransactionRepository transactionRepository,
      SettingsRepository settingsRepository) {
    return new HistoryViewModel(transactionRepository, settingsRepository);
  }
}
