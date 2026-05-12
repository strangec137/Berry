package com.myapp.buckwheat.domain.usecase

import com.myapp.buckwheat.data.local.entity.TransactionEntity
import javax.inject.Inject

class CalculateBalanceUseCase @Inject constructor() {

    operator fun invoke(transactions: List<TransactionEntity>): Double {
        return transactions.sumOf { txn ->
            when (txn.type) {
                "income", "adjustment_add" -> txn.amount
                else -> -txn.amount  // expense, adjustment
            }
        }
    }
}
