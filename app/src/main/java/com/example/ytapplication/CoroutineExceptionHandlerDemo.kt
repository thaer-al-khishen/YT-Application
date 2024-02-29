package com.example.ytapplication

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineExceptionHandlerDemo {

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        val scope = CoroutineScope(Dispatchers.IO + handler)

        scope.launch {
            try {
                throw RuntimeException("Failed coroutine")
            } catch (e: Exception) {
                println("Custom exception handling ${e}")
            }
        }
    }

}