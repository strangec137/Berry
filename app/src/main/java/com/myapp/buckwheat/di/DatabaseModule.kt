package com.myapp.buckwheat.di

import android.content.Context
import androidx.room.Room
import com.myapp.buckwheat.data.local.AppDatabase
import com.myapp.buckwheat.data.local.AppPreferences
import com.myapp.buckwheat.data.local.dao.PeriodDao
import com.myapp.buckwheat.data.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "buckwheat_database"
        ).build()
    }

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    fun providePeriodDao(database: AppDatabase): PeriodDao {
        return database.periodDao()
    }

    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }
}
