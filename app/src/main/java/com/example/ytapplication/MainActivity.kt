package com.example.ytapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ytapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogAction.setOnClickListener {
            Log.d("ButtonClick", "Clicked on button!")
        }

        CoroutineScope(Dispatchers.IO).launch {
            val userData = fetchUserData()
            processUserData(userData)
        }
        CoroutineScope(Dispatchers.IO).launch {
            fetchNews()
        }
    }

    // Simulate fetching user data from a network or database
    private suspend fun fetchUserData(): String {
        delay(1000) // Simulates a long-running task, e.g., network call
        println("Fetched User Data")
        return "User Data"
    }

    // Simulate processing the fetched user data
    private suspend fun processUserData(data: String) {
        delay(1000) // Simulates processing data
        println("Processed $data")
    }

    private suspend fun fetchNews() {
        delay(1500)
        println("Today's news")
    }

}
