package com.myapp.buckwheat.data.repository

import com.myapp.buckwheat.data.local.dao.TransactionDao
import com.myapp.buckwheat.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface TransactionRepository {
    suspend fun insert(transaction: TransactionEntity): Long
    suspend fun deleteById(id: Long)
    fun getAllTransactions(): Flow<List<TransactionEntity>>
    fun getTransactionsBetween(startDate: String, endDate: String): Flow<List<TransactionEntity>>
    fun getTransactionsOnDate(date: String): Flow<List<TransactionEntity>>
    suspend fun getTransactionsOnDateSync(date: String): List<TransactionEntity>
    suspend fun getTransactionsBetweenSync(startDate: String, endDate: String): List<TransactionEntity>
    fun getDistinctTags(): Flow<List<String>>
    fun getDistinctIncomeSources(): Flow<List<String>>
    suspend fun getTotalIncome(startDate: String, endDate: String): Double
    suspend fun getTotalExpenses(startDate: String, endDate: String): Double
    suspend fun getTotalAdjustmentSubtracted(startDate: String, endDate: String): Double
    suspend fun getTotalAdjustmentAdded(startDate: String, endDate: String): Double
    suspend fun getTotalExpensesOnDate(date: String): Double
    suspend fun getLastIncome(): TransactionEntity?
    suspend fun getExpenseCount(startDate: String, endDate: String): Int
    suspend fun getTransactionCount(): Long
    fun getTransactionsByType(type: String, startDate: String, endDate: String): Flow<List<TransactionEntity>>
    suspend fun deleteAll()
}

@Singleton
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun insert(transaction: TransactionEntity): Long =
        transactionDao.insert(transaction)

    override suspend fun deleteById(id: Long) =
        transactionDao.deleteById(id)

    override fun getAllTransactions(): Flow<List<TransactionEntity>> =
        transactionDao.getAllTransactions()

    override fun getTransactionsBetween(startDate: String, endDate: String): Flow<List<TransactionEntity>> =
        transactionDao.getTransactionsBetween(startDate, endDate)

    override fun getTransactionsOnDate(date: String): Flow<List<TransactionEntity>> =
        transactionDao.getTransactionsOnDate(date)

    override suspend fun getTransactionsOnDateSync(date: String): List<TransactionEntity> =
        transactionDao.getTransactionsOnDateSync(date)

    override suspend fun getTransactionsBetweenSync(startDate: String, endDate: String): List<TransactionEntity> =
        transactionDao.getTransactionsBetweenSync(startDate, endDate)

    override fun getDistinctTags(): Flow<List<String>> =
        transactionDao.getDistinctTags()

    override fun getDistinctIncomeSources(): Flow<List<String>> =
        transactionDao.getDistinctIncomeSources()

    override suspend fun getTotalIncome(startDate: String, endDate: String): Double =
        transactionDao.getTotalIncome(startDate, endDate) ?: 0.0

    override suspend fun getTotalExpenses(startDate: String, endDate: String): Double =
        transactionDao.getTotalExpenses(startDate, endDate) ?: 0.0

    override suspend fun getTotalAdjustmentSubtracted(startDate: String, endDate: String): Double =
        transactionDao.getTotalAdjustmentSubtracted(startDate, endDate) ?: 0.0

    override suspend fun getTotalAdjustmentAdded(startDate: String, endDate: String): Double =
        transactionDao.getTotalAdjustmentAdded(startDate, endDate) ?: 0.0

    override suspend fun getTotalExpensesOnDate(date: String): Double =
        transactionDao.getTotalExpensesOnDate(date) ?: 0.0

    override suspend fun getLastIncome(): TransactionEntity? =
        transactionDao.getLastIncome()

    override suspend fun getExpenseCount(startDate: String, endDate: String): Int =
        transactionDao.getExpenseCount(startDate, endDate)

    override suspend fun getTransactionCount(): Long =
        transactionDao.getTransactionCount()

    override fun getTransactionsByType(type: String, startDate: String, endDate: String): Flow<List<TransactionEntity>> =
        transactionDao.getTransactionsByType(type, startDate, endDate)

    override suspend fun deleteAll() =
        transactionDao.deleteAll()
}
