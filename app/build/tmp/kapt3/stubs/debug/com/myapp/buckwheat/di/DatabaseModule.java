package com.myapp.buckwheat.di;

import android.content.Context;
import androidx.room.Room;
import com.myapp.buckwheat.data.local.AppDatabase;
import com.myapp.buckwheat.data.local.AppPreferences;
import com.myapp.buckwheat.data.local.dao.PeriodDao;
import com.myapp.buckwheat.data.local.dao.TransactionDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/myapp/buckwheat/di/DatabaseModule;", "", "()V", "provideAppDatabase", "Lcom/myapp/buckwheat/data/local/AppDatabase;", "context", "Landroid/content/Context;", "provideAppPreferences", "Lcom/myapp/buckwheat/data/local/AppPreferences;", "providePeriodDao", "Lcom/myapp/buckwheat/data/local/dao/PeriodDao;", "database", "provideTransactionDao", "Lcom/myapp/buckwheat/data/local/dao/TransactionDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.myapp.buckwheat.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.data.local.AppDatabase provideAppDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.data.local.dao.TransactionDao provideTransactionDao(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.local.AppDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.data.local.dao.PeriodDao providePeriodDao(@org.jetbrains.annotations.NotNull()
    com.myapp.buckwheat.data.local.AppDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.myapp.buckwheat.data.local.AppPreferences provideAppPreferences(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}