package com.myapp.buckwheat.domain.usecase

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.myapp.buckwheat.data.local.entity.TransactionEntity
import com.myapp.buckwheat.data.repository.TransactionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

class ExportCsvUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(startDate: String, endDate: String): Intent {
        val transactions = transactionRepository.getTransactionsBetweenSync(startDate, endDate)
        val fileName = "Spends (from $startDate to $endDate).csv"
        val file = File(context.cacheDir, fileName)

        FileWriter(file).use { writer ->
            writer.append("Date,Time,Type,Amount,Currency Symbol,Tag/Source,Note\n")
            transactions.forEach { t ->
                val tagSource = t.tag?.replace(",", ";") ?: ""
                val note = t.note?.replace(",", ";") ?: ""
                writer.append("${t.date},${t.time},${t.type},${String.format("%.2f", t.amount)},${t.currency},$tagSource,$note\n")
            }
        }

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

        return Intent(Intent.ACTION_SEND).apply {
            type = "text/csv"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }
}
