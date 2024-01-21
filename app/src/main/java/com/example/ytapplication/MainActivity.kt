package com.example.ytapplication

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ytapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the saved preference
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getBoolean("DarkMode", false)

        binding.switchDarkMode.isChecked = isDarkMode
        updateUI(isDarkMode)

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Save the preference
            sharedPrefs.edit().putBoolean("DarkMode", isChecked).apply()
            updateUI(isChecked)
        }

    }

    private fun updateUI(isDarkMode: Boolean) {
        if (isDarkMode) {
            binding.textView.setTextColor(Color.WHITE)
            binding.clParent.setBackgroundColor(Color.BLACK)
        } else {
            binding.textView.setTextColor(Color.BLACK)
            binding.clParent.setBackgroundColor(Color.WHITE)
        }
    }

}
