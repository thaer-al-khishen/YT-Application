package com.example.ytapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope

class UserDataFetcher {

    suspend fun fetchUserDataAndPosts() {

        supervisorScope {

            try {
                val userData = async(Dispatchers.IO) { fetchUserData() }
                val userPosts = async(Dispatchers.IO) { fetchUserPosts() }

//                try {
                    println("User Data: ${userData.await()}")
//                } catch (e: Exception) {
//                    println("Failed to fetch user data: ${e.localizedMessage}")
//                }

//                try {
                    println("User Posts: ${userPosts.await()}")
//                } catch (e: Exception) {
//                    println("Failed to fetch user posts: ${e.localizedMessage}")
//                }
            } catch (e: Exception) {
                println("Failed : ${e.localizedMessage}")            }

        }

    }

    private suspend fun fetchUserData(): String {
        // simulate network request
        delay(1000)
        throw Exception("Failed fetching user data")
    }

    private suspend fun fetchUserPosts(): String {
        // simulate another network request
        delay(1000)
        return "User posts content"
    }

}
