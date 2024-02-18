package com.example.ytapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NetworkConnectivityHandler(appCompatActivity: AppCompatActivity) {

    val connectivityManager = appCompatActivity.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    lateinit var networkCallback: ConnectivityManager.NetworkCallback

    init {
        GlobalScope.launch {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    // Handle network being available
                    Log.d("NetworkConnection", "Network is available")
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    // Handle network being lost
                    Log.d("NetworkConnection", "Network is lost")
                }
            }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        }
    }

}
