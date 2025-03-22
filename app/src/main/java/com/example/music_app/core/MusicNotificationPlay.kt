//@file:Suppress("UNUSED_EXPRESSION")
//
//package com.example.music_app.core
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Build
//import android.support.v4.media.session.MediaSessionCompat
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import androidx.core.app.ServiceCompat.startForeground
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.music_app.MainActivity
//import com.example.music_app.R
//import com.example.music_app.factory.TrackBroadcastReceiver
//import com.example.music_app.factory.TrackService
//import com.example.music_app.presentation.track_play.components.ActionType
//import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
//import kotlinx.coroutines.coroutineScope
//
//@Composable
//fun MusicNotification(
//    viewModel: TrackPlayViewModel= hiltViewModel(),
//) {
//
//    val track=viewModel.track.observeAsState()
//    val isPlaying by viewModel.isPlaying.collectAsState(false)
//
//
//
//    // Tạo MediaSession
//    val mediaSession = MediaSessionCompat(context, "MusicService")
//
//    // Intent để mở ứng dụng khi nhấn vào thông báo
//    val intent = Intent(context, MainActivity::class.java)
//    val pendingIntent: PendingIntent =
//        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//    val pauseIntent= PendingIntent.getService(context, 1, Intent(context,TrackService::class.java).apply {
//        action="PAUSE"
//
//    }, PendingIntent.FLAG_IMMUTABLE)
//
//    val playIntent= PendingIntent.getService(context, 1, Intent(context,TrackService::class.java).apply {
//        action="PLAY"
//    }, PendingIntent.FLAG_IMMUTABLE)
//
//
//    val prevIntent= PendingIntent.getService(context, 1, Intent(context,TrackService::class.java).apply {
//        action="PREVIOUS"
//    }, PendingIntent.FLAG_IMMUTABLE)
//
//    val intentNext= Intent(context,TrackBroadcastReceiver::class.java).apply {
//        this.action=action
//    }
//    val pendingIntentNext=PendingIntent.getBroadcast(context,0,intentNext,PendingIntent.FLAG_IMMUTABLE)
//    // Tạo thông báo
//    val notification: Notification = NotificationCompat.Builder(context, channelId)
//        .setSmallIcon(R.drawable.song_item) // Icon của thông báo
//        .setContentTitle(track.value?.name ?: "")
//        .setContentText(track.value?.artist_name ?:"") // Ca si
//        .setContentIntent(pendingIntent)
//        .setPriority(NotificationCompat.PRIORITY_LOW)
//        .setStyle(
//            androidx.media.app.NotificationCompat.MediaStyle()
//                .setMediaSession(mediaSession.sessionToken)
//        )
//        .addAction(R.drawable.fast_rewind_24, "Previous", getPendingIntent("PREVIOUS",context)) // Nút "Previous"
//        .addAction(if (isPlaying) R.drawable.pause_24 else R.drawable.play_24, "Pause", if(isPlaying) pauseIntent else playIntent) // Nút "Pause"
//        .addAction(R.drawable.fast_forward_24, "Next", pendingIntentNext) // Nút "Next"
//        .build()
//
////    // Hiển thị thông báo
//    with(NotificationManagerCompat.from(context)) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            notify(notificationId, notification)
//            return
//        }
//    }
//
//
//
//}
//
//private fun getPendingIntent(action:String,context: Context):PendingIntent{
//    val intent= Intent(context,TrackBroadcastReceiver::class.java).apply {
//        this.action=action
//    }
//    return PendingIntent.getBroadcast(context,action.hashCode(),intent,PendingIntent.FLAG_IMMUTABLE)
//}
