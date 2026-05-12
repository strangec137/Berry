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
public final class FormatAmountUseCase_Factory implements Factory<FormatAmountUseCase> {
  @Override
  public FormatAmountUseCase get() {
    return newInstance();
  }

  public static FormatAmountUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FormatAmountUseCase newInstance() {
    return new FormatAmountUseCase();
  }

  private static final class InstanceHolder {
    private static final FormatAmountUseCase_Factory INSTANCE = new FormatAmountUseCase_Factory();
  }
}
