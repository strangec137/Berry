package com.myapp.buckwheat.data.repository

import com.myapp.buckwheat.data.local.dao.PeriodDao
import com.myapp.buckwheat.data.local.entity.PeriodEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface PeriodRepository {
    suspend fun insert(period: PeriodEntity): Long
    fun getAllPeriods(): Flow<List<PeriodEntity>>
    suspend fun getLatestPeriod(): PeriodEntity?
    suspend fun deleteAll()
}

@Singleton
class PeriodRepositoryImpl @Inject constructor(
    private val periodDao: PeriodDao
) : PeriodRepository {

    override suspend fun insert(period: PeriodEntity): Long =
        periodDao.insert(period)

    override fun getAllPeriods(): Flow<List<PeriodEntity>> =
        periodDao.getAllPeriods()

    override suspend fun getLatestPeriod(): PeriodEntity? =
        periodDao.getLatestPeriod()

    override suspend fun deleteAll() =
        periodDao.deleteAll()
}
