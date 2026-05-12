package com.myapp.buckwheat.domain.usecase;

import android.content.Context;
import com.myapp.buckwheat.data.repository.TransactionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ExportCsvUseCase_Factory implements Factory<ExportCsvUseCase> {
  private final Provider<Context> contextProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public ExportCsvUseCase_Factory(Provider<Context> contextProvider,
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public ExportCsvUseCase get() {
    return newInstance(contextProvider.get(), transactionRepositoryProvider.get());
  }

  public static ExportCsvUseCase_Factory create(Provider<Context> contextProvider,
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new ExportCsvUseCase_Factory(contextProvider, transactionRepositoryProvider);
  }

  public static ExportCsvUseCase newInstance(Context context,
      TransactionRepository transactionRepository) {
    return new ExportCsvUseCase(context, transactionRepository);
  }
}
