package com.example.ytapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _userFlow = MutableStateFlow("")
    val userFlow: StateFlow<String> = _userFlow.asStateFlow()

    fun loadUserData() {
        viewModelScope.launch {
            delay(2000) // Simulate network request
            _userFlow.update {
                "Retrieved user data"
            }
        }
    }

}
