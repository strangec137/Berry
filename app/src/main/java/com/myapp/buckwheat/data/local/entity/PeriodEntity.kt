package com.myapp.buckwheat.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "periods")
data class PeriodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val startDate: String,
    val endDate: String,
    val totalIncome: Double,
    val totalSpent: Double,
    val currency: String,
    val createdAt: Long
)
