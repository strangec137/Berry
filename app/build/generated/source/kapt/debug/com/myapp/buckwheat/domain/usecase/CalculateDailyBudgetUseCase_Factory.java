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
public final class CalculateDailyBudgetUseCase_Factory implements Factory<CalculateDailyBudgetUseCase> {
  @Override
  public CalculateDailyBudgetUseCase get() {
    return newInstance();
  }

  public static CalculateDailyBudgetUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateDailyBudgetUseCase newInstance() {
    return new CalculateDailyBudgetUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateDailyBudgetUseCase_Factory INSTANCE = new CalculateDailyBudgetUseCase_Factory();
  }
}
