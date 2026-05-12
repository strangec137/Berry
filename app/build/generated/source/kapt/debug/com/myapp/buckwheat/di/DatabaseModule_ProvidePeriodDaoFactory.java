package com.myapp.buckwheat.di;

import com.myapp.buckwheat.data.local.AppDatabase;
import com.myapp.buckwheat.data.local.dao.PeriodDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvidePeriodDaoFactory implements Factory<PeriodDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvidePeriodDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PeriodDao get() {
    return providePeriodDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePeriodDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePeriodDaoFactory(databaseProvider);
  }

  public static PeriodDao providePeriodDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePeriodDao(database));
  }
}
