package com.example.ytapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ytapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NetworkConnectivityHandler(this)
        val countdownTimerManager = CountdownTimerManager()

        countdownTimerManager.startTimer(60) {
            binding.textView.text = "Seconds left: ${it}"
        }

        binding.btnStopTimer.setOnClickListener {
            countdownTimerManager.cancelTimer()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.userFlow.collect {
                    binding.tvUserData.text = it
                }
            }
        }

        binding.btnGetUserData.setOnClickListener {
            mainViewModel.loadUserData()
//            loadUserData()
        }

        binding.btnNavigate.setOnClickListener {
            startActivity(Intent(this, DestinationActivity::class.java))
        }

    }

    private fun loadUserData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                delay(2000)
                Log.d("CoroutineOutput", "Retrieved user data")
            }
        }
    }

}
