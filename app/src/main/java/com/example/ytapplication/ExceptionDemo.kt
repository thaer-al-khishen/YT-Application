package com.example.ytapplication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExceptionDemo {

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            try {
                taskThatFails()
                taskThatSucceeds()
            } catch (e: Exception) {
                println("Caught an exception in the coroutine scope: ${e.message}")
            }
        }
    }

    private suspend fun taskThatFails() {
        delay(500) // Simulate some work
        throw Exception("Failed in taskThatFails")
    }

    private suspend fun taskThatSucceeds() {
        delay(1000) // Simulate some work
        println("Succeeded in taskThatSucceeds")
    }

}
