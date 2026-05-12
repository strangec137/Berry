package com.myapp.buckwheat.di;

import android.content.Context;
import com.myapp.buckwheat.data.local.AppPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideAppPreferencesFactory implements Factory<AppPreferences> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideAppPreferencesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AppPreferences get() {
    return provideAppPreferences(contextProvider.get());
  }

  public static DatabaseModule_ProvideAppPreferencesFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideAppPreferencesFactory(contextProvider);
  }

  public static AppPreferences provideAppPreferences(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAppPreferences(context));
  }
}
