package com.example.music_app.ui.components

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.music_app.MainActivity
import com.example.music_app.R
import com.example.music_app.factory.PlayService

@Composable
fun MusicNotification() {
    val context = LocalContext.current
    val notificationId = 1
    val channelId = "music_channel"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Music Channel"
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = "Channel for music notification"
        }

        // Đăng ký channel
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // Tạo MediaSession
    val mediaSession = MediaSessionCompat(context, "MusicService")

    // Intent để mở ứng dụng khi nhấn vào thông báo
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    //begin-pause
    val pauseIntent= Intent(context,PlayService::class.java).apply {
        action="ACTION_PAUSE"
    }
    val pausePendingIntent: PendingIntent =
        PendingIntent.getService(context, 1, pauseIntent, PendingIntent.FLAG_IMMUTABLE)
    //end-pause

    // Tạo thông báo
    val notification: Notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.song_item) // Icon của thông báo
        .setContentTitle("Now Playing")
        .setContentText("Your song title") // Tên bài hát
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setStyle(
            androidx.media.app.NotificationCompat.MediaStyle()
                .setMediaSession(mediaSession.sessionToken)
        )
        .addAction(R.drawable.fast_rewind_24, "Previous", pendingIntent) // Nút "Previous"
        .addAction(R.drawable.pause_24, "Pause", pausePendingIntent) // Nút "Pause"
        .addAction(R.drawable.fast_forward_24, "Next", pendingIntent) // Nút "Next"
        .build()

    // Hiển thị thông báo
    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notify(notificationId, notification)
            
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
    }
}