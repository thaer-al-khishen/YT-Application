package com.example.ytapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ServiceAction.PLAY_MUSIC.toString() -> playMusic()
            ServiceAction.STOP_MUSIC.toString() -> stopMusic()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun playMusic() {
        // Create a notification
        val notification = createNotification()

        // Start foreground service
        startForeground(1, notification)

        // Perform the service task here (e.g., play music)
        // Initialize MediaPlayer and start playing music
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_music)
        mediaPlayer?.start()
    }

    private fun stopMusic() {
        //Stop the music
        stopAndReleaseMediaPlayer()

        //Stop the foreground service
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun stopAndReleaseMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun createNotification(): Notification {
        val builder = NotificationCompat.Builder(this, "MY_SERVICE_CHANNEL")
            .setContentTitle("Music Player")
            .setContentText("Playing your favorite music!")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setSilent(true)

        return builder.build()
    }

    override fun onDestroy() {
        stopAndReleaseMediaPlayer()
        super.onDestroy()
    }

}
