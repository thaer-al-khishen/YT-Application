package com.example.ytapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ytapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d("UserDetails", "Launched at: ${System.currentTimeMillis()}")
                fetchUserDetails().also {
                    Log.d("UserDetails", "Retrieved at: ${System.currentTimeMillis()}")
                    Log.d("UserDetails", it.name)
                    Log.d("UserDetails", it.email)
                }
            }
        }

    }

    data class UserDetails(
        val name: String,
        val email: String
    )

    private suspend fun fetchUserDetails(): UserDetails = coroutineScope {
        val nameDeferred = async { fetchUserName() } // Fetch user profile
        val emailDeferred = async { fetchUserEmail() } // Fetch user statistics

        val name = nameDeferred.await() // Wait for the user profile
        val email = emailDeferred.await() // Wait for the user stats

        UserDetails(name, email) // Combine data into a single object
    }

    private suspend fun fetchUserName(): String {
        // Simulate network call
        delay(1000) // Placeholder for a network call
        return "John Doe"
    }

    private suspend fun fetchUserEmail(): String {
        // Simulate another network call
        delay(800) // Placeholder for a network call
        return "john@example.com"
    }

}
