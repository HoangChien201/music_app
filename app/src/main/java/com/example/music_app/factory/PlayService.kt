package com.example.music_app.factory

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class PlayService:Service() {

    override fun onBind(intent: Intent?): IBinder? {
        // Không sử dụng Bound Service trong trường hợp này
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "ACTION_PAUSE" -> SongFactory.pause()
        }
        return START_STICKY
    }
}