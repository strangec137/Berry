package com.myapp.buckwheat.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CalculateBalanceUseCase_Factory implements Factory<CalculateBalanceUseCase> {
  @Override
  public CalculateBalanceUseCase get() {
    return newInstance();
  }

  public static CalculateBalanceUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateBalanceUseCase newInstance() {
    return new CalculateBalanceUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateBalanceUseCase_Factory INSTANCE = new CalculateBalanceUseCase_Factory();
  }
}
