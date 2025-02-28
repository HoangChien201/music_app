package com.example.music_app.factory

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

sealed class SongType{
    data class SongIDType(val value:Int,val id:Int):SongType()
    data class SongUrlType(val value:String,val id:Int):SongType()
}

object SongFactory {
    private var mediaPlayer: MediaPlayer? = null
    var isPlaying: Boolean = false
        private set
    var songCurrent:Int?=null
    var totalTime:Int?=null

    fun initialize(context: Context, songType:SongType ) {
        when(songType){
            is SongType.SongIDType ->{
                val value=songType.value
                if (songType.id == songCurrent  && mediaPlayer != null) {
                    return
                }

                release() // Giải phóng tài nguyên cũ (nếu có)
                songCurrent=songType.id
                mediaPlayer = MediaPlayer.create(context, value).apply {
                    setOnCompletionListener {

                        release()
                    }
                }
                this.totalTime= mediaPlayer!!.duration
            }
            is SongType.SongUrlType ->{
                val url = songType.value // your URL here
                if (songType.id == songCurrent  && mediaPlayer != null) {
                    return
                }

                release() // Giải phóng tài nguyên cũ (nếu có)
                songCurrent=songType.id
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
            }
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }

}