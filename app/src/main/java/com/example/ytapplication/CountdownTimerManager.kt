package com.example.ytapplication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CountdownTimerManager {
    private var timerJob: Job? = null // Job to manage the timer coroutine, allowing cancellation
    private val timerScope = CoroutineScope(Dispatchers.Main) // Use Main for UI updates
    // Start a countdown timer for a specified duration in seconds
    fun startTimer(durationSeconds: Int, onUpdate: (Int) -> Unit) {
        // Cancel any existing timer before starting a new one
        timerJob?.cancel()
        timerJob = timerScope.launch {
            var remainingTime = durationSeconds
            while (remainingTime >= 0 && isActive) {
                onUpdate(remainingTime) // Update UI with the remaining time
                delay(1000) // Wait for 1 second
                remainingTime-- // Decrement the remaining time
            }
        }
    }
    // Cancel the countdown timer
    fun cancelTimer() {
        timerJob?.cancel()
    }

}
