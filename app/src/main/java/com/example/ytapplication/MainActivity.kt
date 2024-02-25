package com.example.ytapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ytapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countdownTimerManager = CountdownTimerManager()

        countdownTimerManager.startTimer(60) {
            binding.tvTimer.text = "Seconds left: ${it}"
        }

        binding.btnStopTimer.setOnClickListener {
            countdownTimerManager.cancelTimer()
        }

        binding.btnFetchData.setOnClickListener {
            viewModel.loadDashboardData()
        }

    }

}
