package com.example.ytapplication

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class UserDataSyncWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Increment a counter here. For this example, we'll just log a message.
        // Ideally, you would update this value in a database or shared preferences.
        println("Syncing user data")

        return Result.success()
    }

}
