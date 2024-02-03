package com.example.ytapplication

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class FetchWorldWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Simulate some delay
        Thread.sleep(3000)

        // Get input data
        val inputString = inputData.getString("result") ?: ""
        val outputString = "$inputString World" // Concatenate the strings

        // Output data
        val outputData = Data.Builder()
            .putString("result", outputString)
            .build()

        println(outputString)
        return Result.success(outputData)
    }
}