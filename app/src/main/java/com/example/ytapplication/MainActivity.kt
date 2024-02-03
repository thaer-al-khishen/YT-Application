package com.example.ytapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.ytapplication.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Schedule the Image Upload Work
        binding.btnUploadImage.setOnClickListener {
            val uploadWorkRequest = OneTimeWorkRequestBuilder<ImageUploadWorker>().build()
            WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        }

        // Define the periodic work request
        val periodicWorkRequest = PeriodicWorkRequestBuilder<UserDataSyncWorker>(15, TimeUnit.MINUTES)
            // Additional configuration like constraints can be added here
            .build()

        // Enqueue the periodic work
        binding.btnSyncUserData.setOnClickListener {
            WorkManager.getInstance(this).enqueue(periodicWorkRequest)
        }

        // Chain the "Hello" and "World" Work
        val fetchHelloRequest = OneTimeWorkRequestBuilder<FetchHelloWorker>().build()
        val fetchWorldRequest = OneTimeWorkRequestBuilder<FetchWorldWorker>().build()

        binding.btnChainRequest.setOnClickListener {
            WorkManager.getInstance(this)
                .beginWith(fetchHelloRequest)
                .then(fetchWorldRequest)
                .enqueue()
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(fetchWorldRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    // Get the result
                    val result = workInfo.outputData.getString("result")
                    binding.tvChainRequests.text = result
                }
            }

    }

}
