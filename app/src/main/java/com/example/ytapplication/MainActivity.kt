package com.example.ytapplication

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //System
    private val screenReceiver = ScreenOnOffReceiver()

    //Local
    private val customReceiver = CustomReceiver()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intent filter to specify which actions the receiver should listen to
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
        }

        // Register the receiver
        registerReceiver(screenReceiver, filter)

        val customFilter = IntentFilter().apply {
            addAction("ACTION_MY_BROADCAST")
        }

        registerReceiver(customReceiver, customFilter, RECEIVER_NOT_EXPORTED)

        findViewById<Button>(R.id.btn_click).setOnClickListener {
            val intent = Intent("DESTINATION_BROADCAST")
            sendBroadcast(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the receiver
        unregisterReceiver(screenReceiver)
        unregisterReceiver(customReceiver)
    }

}
