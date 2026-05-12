package com.myapp.buckwheat.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val type: String,           // "income" or "expense"
    val amount: Double,
    val currencySymbol: String,
    val tag: String?,
    val note: String?,
    val date: LocalDate,
    val time: LocalDateTime
)

fun com.myapp.buckwheat.data.local.entity.TransactionEntity.toDomain(symbol: String = ""): Transaction {
    return Transaction(
        id = id,
        type = type,
        amount = amount,
        currencySymbol = symbol.ifEmpty { currency },
        tag = tag,
        note = note,
        date = LocalDate.parse(date),
        time = LocalDateTime.parse("${date}T${time}")
    )
}
