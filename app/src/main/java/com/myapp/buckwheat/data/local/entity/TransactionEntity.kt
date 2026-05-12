package com.myapp.buckwheat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,           // "income" or "expense"
    val amount: Double,
    val currency: String,
    val tag: String?,           // tag for expense; source label for income
    val note: String?,
    val date: String,           // "yyyy-MM-dd"
    val time: String,           // "HH:mm:ss"
    val createdAt: Long         // epoch millis
)
