package com.example.ytapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ScreenOnOffReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_OFF -> Log.d("ScreenReceiver", "Screen turned off")
            Intent.ACTION_SCREEN_ON -> Log.d("ScreenReceiver", "Screen turned on")
        }
    }
}
