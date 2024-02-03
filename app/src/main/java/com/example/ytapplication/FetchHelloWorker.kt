package com.example.ytapplication

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class FetchHelloWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Output data
        val outputData = Data.Builder()
            .putString("result", "Hello")
            .build()

        return Result.success(outputData)
    }
}
