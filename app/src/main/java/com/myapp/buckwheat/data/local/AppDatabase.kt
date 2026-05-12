package com.myapp.buckwheat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.buckwheat.data.local.dao.PeriodDao
import com.myapp.buckwheat.data.local.dao.TransactionDao
import com.myapp.buckwheat.data.local.entity.PeriodEntity
import com.myapp.buckwheat.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class, PeriodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun periodDao(): PeriodDao
}
