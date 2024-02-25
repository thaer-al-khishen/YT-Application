package com.example.ytapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DashboardViewModel : ViewModel() {

    fun loadDashboardData() {
        viewModelScope.launch {
            try {
                supervisorScope {
                    val userProfileDeferred = async { loadUserProfile() }
                    val latestPostsDeferred = async { loadLatestPosts() }
                    val friendActivityDeferred = async { loadFriendActivity() }

                    try {
                        userProfileDeferred.await().also { Log.d("DataOutput", it) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    try {
                        latestPostsDeferred.await().also { Log.d("DataOutput", it) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    try {
                        friendActivityDeferred.await().also { Log.d("DataOutput", it) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Assume these methods make network requests
    private suspend fun loadUserProfile(): String {
        delay(500)
        return "Profile"
    }

    private suspend fun loadLatestPosts(): String {
        delay(1000)
        throw Exception("Error occured while retrieving latest posts")
//        return "Posts"
    }

    private suspend fun loadFriendActivity(): String {
        delay(2000)
        return "Friend activity"
    }

}
