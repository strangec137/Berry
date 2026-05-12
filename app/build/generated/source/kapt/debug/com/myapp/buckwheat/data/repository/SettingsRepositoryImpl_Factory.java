package com.myapp.buckwheat.data.repository;

import com.myapp.buckwheat.data.local.AppPreferences;
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
public final class SettingsRepositoryImpl_Factory implements Factory<SettingsRepositoryImpl> {
  private final Provider<AppPreferences> prefsProvider;

  public SettingsRepositoryImpl_Factory(Provider<AppPreferences> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public SettingsRepositoryImpl get() {
    return newInstance(prefsProvider.get());
  }

  public static SettingsRepositoryImpl_Factory create(Provider<AppPreferences> prefsProvider) {
    return new SettingsRepositoryImpl_Factory(prefsProvider);
  }

  public static SettingsRepositoryImpl newInstance(AppPreferences prefs) {
    return new SettingsRepositoryImpl(prefs);
  }
}
