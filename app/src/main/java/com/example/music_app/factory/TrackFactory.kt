package com.example.music_app.factory

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

sealed class TrackType{
    data class TrackIDType(val value:Int,val id:String):TrackType()
    data class TrackUrlType(val value:String,val id:String):TrackType()
}

object TrackFactory {
    private var mediaPlayer: MediaPlayer? = null
    var isPlaying: Boolean = false
        private set
    var trackCurrent:String?=null
    var totalTime:Int?=null
    var stop:Boolean=false

    fun initialize(context: Context, trackType:TrackType ) {
        when(trackType){
            is TrackType.TrackIDType ->{
                val value=trackType.value
                if (trackType.id == trackCurrent  && mediaPlayer != null) {
                    return
                }

                release() // Giải phóng tài nguyên cũ (nếu có)
                trackCurrent=trackType.id
                mediaPlayer = MediaPlayer.create(context, value).apply {
                    setOnCompletionListener {

                        release()
                    }
                }
                this.totalTime= mediaPlayer!!.duration
            }
            is TrackType.TrackUrlType ->{
                val url = trackType.value // your URL here
                if (trackType.id == trackCurrent  && mediaPlayer != null) {
                    return
                }

                release() // Giải phóng tài nguyên cũ (nếu có)
                trackCurrent=trackType.id
                try {
                    mediaPlayer = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        setDataSource(url)
                        prepare() // might take long! (for buffering, etc)
                    }
                    this.totalTime= mediaPlayer!!.duration
                }catch (e: IOException) {
                    Log.e("MediaPlayer", "Error setting data source: ${e.message}")
                }

            }
        }

    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()

                isPlaying = true
            }
        }
    }

    fun pause() {
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

    fun stop() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                isPlaying = false
                stop=true
            }
        }
    }

    fun getCurrentPosition(): Int {
        var positionTrack=0
        mediaPlayer?.let {
            positionTrack= it.currentPosition
        }
        return positionTrack
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }

}