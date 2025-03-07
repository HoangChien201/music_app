package com.example.music_app.factory

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import java.io.IOException


class TrackService:Service() {
    private var mediaPlayer: MediaPlayer? = null
    var isPlaying: Boolean = false
        private set
    var trackCurrent:String?=null
    var totalTime:Int?=null
    var stop:Boolean=false

    override fun onBind(intent: Intent?): IBinder? {
        // Không sử dụng Bound Service trong trường hợp này
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "PLAY" -> this.play()
            "PAUSE" -> this.pause()
            "STOP" -> this.stop()
            "NEXT" -> this.next()
            "PREVIOUS" -> this.previous()
            "START" -> this.start(intent)
        }
        return START_STICKY
    }

    private fun start(intent: Intent?){
//        val trackId = intent?.getIntExtra("trackId", 0)
        val url = intent?.getStringExtra("url")
        mediaPlayer = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        setDataSource(url)
                        prepare() // might take long! (for buffering, etc)
                        start()
                    }

    }

    private fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()

                isPlaying = true
            }
        }
    }

    private fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                Log.d("AAA", "currentPosition"+it.currentPosition.toFloat()/3600)
                Log.d("AAA", "duration"+it.duration/3600)
                Log.d("AAA", "trackInfo"+it.trackInfo)

                isPlaying = false
            }
        }
    }

    private fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                isPlaying = false
                TrackFactory.stop =true
            }
        }
    }

    private fun getCurrentPosition(): Int {
        var positionTrack=0
        mediaPlayer?.let {
            positionTrack= it.currentPosition
        }
        return positionTrack
    }

    private fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }

    private fun next(){

    }

    private fun previous(){

    }
}