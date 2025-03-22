package com.example.music_app.factory

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.music_app.MainActivity
import com.example.music_app.R
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject


@AndroidEntryPoint
class TrackService:Service() {
    @Inject lateinit var trackRepo: TrackRepo
    private val binder= TrackServiceBinder()
    private val notificationId = 1
    private val channelId = "music_channel"
    private var mediaPlayer:MediaPlayer?=null
    private var isTrackPlaying=false

    val tracksNext=MutableStateFlow<List<Track>>(emptyList())

    private val _currentPositionService= MutableStateFlow(0)
    val currentPositionService:StateFlow<Int> get()=_currentPositionService

    inner class TrackServiceBinder : Binder() {
        fun getService(): TrackService = this@TrackService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private val updateJob = CoroutineScope(Dispatchers.Main).launch {

        while (isActive) {
            mediaPlayer!!.let {
                if (it.isPlaying) {
                    _currentPositionService.value = it.currentPosition
                }
            }

            delay(1000)  // Cập nhật mỗi giây
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        CoroutineScope(Dispatchers.Main).launch {
            trackRepo.tracksNextState.collectLatest {
                tracksNext.value=it
            }
        }
        when (intent?.action) {
            "PLAY" -> this.play()
            "PAUSE" -> this.pause()
            "STOP" -> this.stop()
            "NEXT" -> this.next()
            "PREVIOUS" -> this.previous()
            "START" -> {
                val trackJson=intent.getStringExtra("track_json")
                val track=Gson().fromJson(trackJson,Track::class.java)
                this.start(track)
            }
        }
        return START_STICKY
    }

    private fun start(track: Track?){
        release()
        try {
            if(track!=null) {
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(track.audio)

                    setOnPreparedListener {
                        start()
                        isTrackPlaying=true
                        trackRepo.insertTrackNextState(track)
                        trackRepo.changeTrackState(track)
                        showNotification(track.name ?: "", track.artist_name ?: "")
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        delay(5000)
                        if (!mediaPlayer!!.isPlaying) {
                            trackRepo.changeTrackState(track)
                            next() // Chuyển bài hát nếu quá thời gian
                        }
                    }

                    prepareAsync()
                }

            }
        }catch (ex:Exception){
            Log.d("TrackService", "Start: ${ex.message}")
        }

    }

    private fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
                isTrackPlaying = true
            }
        }
    }

    private fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isTrackPlaying = false
            }
        }
    }

    private fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                isTrackPlaying = false
            }
        }
    }

    private fun release() {
        mediaPlayer?.release()
        isTrackPlaying = false
    }

    private fun next(){
        //lay track phia sau
        val trackCurrentIndex=tracksNext.value.indexOf(trackRepo.trackCurrentState.value)
        if(trackCurrentIndex!=-1){
            start(tracksNext.value[trackCurrentIndex+1])
        }
    }

    private fun previous(){
        //lay track phia truoc
        val trackCurrentIndex=tracksNext.value.indexOf(trackRepo.trackCurrentState.value)
        if(trackCurrentIndex!=-1 && trackCurrentIndex>0){
            start(tracksNext.value[trackCurrentIndex-1])
        }
    }

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Music Channel"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = "Channel for music notification"
            }

            // Đăng ký channel
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("ForegroundServiceType")
    fun showNotification(trackName:String, artistName:String){
        val mediaSession = MediaSessionCompat(this, "MusicService")

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val pauseIntent= getPendingIntent("PAUSE",this)

        val playIntent= getPendingIntent("PLAY",this)


        val nextIntent= getPendingIntent("NEXT",this)
        val prevIntent= getPendingIntent("PREVIOUS",this)



        // Tạo thông báo
        val notification:Notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.song_item) // Icon của thông báo
            .setContentTitle(trackName)
            .setContentText(artistName) // Ca si
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
            )
            .addAction(R.drawable.fast_rewind_24, "Previous", prevIntent) // Nút "Previous"
            .addAction(if (isTrackPlaying) R.drawable.pause_24 else R.drawable.play_24, "Pause", if(isTrackPlaying) pauseIntent else playIntent) // Nút "Pause"
             .addAction(R.drawable.fast_forward_24, "Next", nextIntent) // Nút "Next"
            .build()
        this.startForeground(notificationId, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        updateJob.cancel()
        release()
    }

    private fun getPendingIntent(action:String,context: Context):PendingIntent{
        val intent= Intent(context,TrackService::class.java).apply {
            this.action=action
        }

        return PendingIntent.getService(context,action.hashCode(),intent,PendingIntent.FLAG_IMMUTABLE)
    }
}