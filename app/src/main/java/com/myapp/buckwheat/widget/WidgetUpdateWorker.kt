package com.myapp.buckwheat.widget

import android.content.Context
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.myapp.buckwheat.widget.extend.ExtendWidget
import com.myapp.buckwheat.widget.minimal.MinimalWidget

class WidgetUpdateWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        MinimalWidget().updateAll(applicationContext)
        ExtendWidget().updateAll(applicationContext)
        return Result.success()
    }
}
