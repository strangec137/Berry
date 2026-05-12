package com.myapp.buckwheat.data.repository;

import com.myapp.buckwheat.data.local.dao.PeriodDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class PeriodRepositoryImpl_Factory implements Factory<PeriodRepositoryImpl> {
  private final Provider<PeriodDao> periodDaoProvider;

  public PeriodRepositoryImpl_Factory(Provider<PeriodDao> periodDaoProvider) {
    this.periodDaoProvider = periodDaoProvider;
  }

  @Override
  public PeriodRepositoryImpl get() {
    return newInstance(periodDaoProvider.get());
  }

  public static PeriodRepositoryImpl_Factory create(Provider<PeriodDao> periodDaoProvider) {
    return new PeriodRepositoryImpl_Factory(periodDaoProvider);
  }

  public static PeriodRepositoryImpl newInstance(PeriodDao periodDao) {
    return new PeriodRepositoryImpl(periodDao);
  }
}
