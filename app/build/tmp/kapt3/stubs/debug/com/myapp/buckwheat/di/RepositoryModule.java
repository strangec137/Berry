package com.myapp.buckwheat.di;

import com.myapp.buckwheat.data.repository.*;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'\u00a8\u0006\r"}, d2 = {"Lcom/myapp/buckwheat/di/RepositoryModule;", "", "()V", "bindPeriodRepository", "Lcom/myapp/buckwheat/data/repository/PeriodRepository;", "impl", "Lcom/myapp/buckwheat/data/repository/PeriodRepositoryImpl;", "bindSettingsRepository", "Lcom/myapp/buckwheat/data/repository/SettingsRepository;", "Lcom/myapp/buckwheat/data/repository/SettingsRepositoryImpl;", "bindTransactionRepository", "Lcom/myapp/buckwheat/data/repository/TransactionRepository;", "Lcom/myapp/buckwheat/data/repository/TransactionRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.buckwheat.data.repository.TransactionRepository bindTransactionRepository(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.TransactionRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.buckwheat.data.repository.SettingsRepository bindSettingsRepository(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.SettingsRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.myapp.buckwheat.data.repository.PeriodRepository bindPeriodRepository(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.repository.PeriodRepositoryImpl impl);
}