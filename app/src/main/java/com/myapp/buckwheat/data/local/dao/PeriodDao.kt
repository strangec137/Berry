package com.myapp.buckwheat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapp.buckwheat.data.local.entity.PeriodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeriodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(period: PeriodEntity): Long

    @Query("SELECT * FROM periods ORDER BY createdAt DESC")
    fun getAllPeriods(): Flow<List<PeriodEntity>>

    @Query("SELECT * FROM periods ORDER BY createdAt DESC LIMIT 1")
    suspend fun getLatestPeriod(): PeriodEntity?

    @Query("DELETE FROM periods")
    suspend fun deleteAll()
}
