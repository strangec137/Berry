package com.myapp.buckwheat.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapp.buckwheat.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity): Long

    @Delete
    suspend fun delete(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM transactions ORDER BY date DESC, time DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, time DESC")
    fun getTransactionsBetween(startDate: String, endDate: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE date = :date ORDER BY time DESC")
    fun getTransactionsOnDate(date: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE date = :date ORDER BY time DESC")
    suspend fun getTransactionsOnDateSync(date: String): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, time DESC")
    suspend fun getTransactionsBetweenSync(startDate: String, endDate: String): List<TransactionEntity>

    @Query("SELECT DISTINCT tag FROM transactions WHERE tag IS NOT NULL AND tag != '' AND type = 'expense' ORDER BY tag ASC")
    fun getDistinctTags(): Flow<List<String>>

    @Query("SELECT DISTINCT tag FROM transactions WHERE tag IS NOT NULL AND tag != '' AND type = 'income' ORDER BY tag ASC")
    fun getDistinctIncomeSources(): Flow<List<String>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'income' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalIncome(startDate: String, endDate: String): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'expense' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalExpenses(startDate: String, endDate: String): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'adjustment' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalAdjustmentSubtracted(startDate: String, endDate: String): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'adjustment_add' AND date BETWEEN :startDate AND :endDate")
    suspend fun getTotalAdjustmentAdded(startDate: String, endDate: String): Double?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'expense' AND date = :date")
    suspend fun getTotalExpensesOnDate(date: String): Double?

    @Query("SELECT * FROM transactions WHERE type = 'income' ORDER BY date DESC, time DESC LIMIT 1")
    suspend fun getLastIncome(): TransactionEntity?

    @Query("SELECT COUNT(*) FROM transactions WHERE type = 'expense' AND date BETWEEN :startDate AND :endDate")
    suspend fun getExpenseCount(startDate: String, endDate: String): Int

    @Query("SELECT COUNT(*) FROM transactions")
    suspend fun getTransactionCount(): Long

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()

    @Query("SELECT * FROM transactions WHERE type = :type AND date BETWEEN :startDate AND :endDate ORDER BY date DESC, time DESC")
    fun getTransactionsByType(type: String, startDate: String, endDate: String): Flow<List<TransactionEntity>>
}
