package com.example.music_app.presentation.track_play.screen

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.usecase.GetTrackUseCase
import com.example.music_app.factory.TrackService
import com.example.music_app.presentation.track_play.components.ActionType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class TrackPlayViewModel @Inject constructor(
    private var getTrackUseCase: GetTrackUseCase,
    @ApplicationContext private var context: Context
): ViewModel(){

    private val _trackCurrent= MutableLiveData<Track>()
    val trackCurrentLiveData: LiveData<Track> get()= _trackCurrent
    private val _tracks= MutableLiveData<List<Track>>()
    val tracksLiveData: LiveData<List<Track>> get()= _tracks

    private val _totalTime= MutableLiveData<Int>()
    val totalTimeLiveData: LiveData<Int> get()= _totalTime

    suspend fun onEvent(action: ActionType){
        when(action){
            is ActionType.Play -> Play()
            is ActionType.Pause -> Pause()
            is ActionType.Stop -> Stop()
            is ActionType.Next -> Next()
            is ActionType.Previous -> Previous()
            is ActionType.Start -> Start(action.value)

        }
    }

    private fun Previous() {
        TODO("Not yet implemented")
    }

    private fun Next() {
        TODO("Not yet implemented")
    }

    private fun Stop() {
        TODO("Not yet implemented")
    }

    private fun Pause() {
        TODO("Not yet implemented")
    }

    private fun Play() {
        TODO("Not yet implemented")
    }

    suspend private fun Start(trackID:Int){
        try {
            var track=getTrackUseCase(trackID)
            _trackCurrent.value=track
            _totalTime.value=track.duration.toInt()

            var intent=Intent(context, TrackService::class.java)
            intent.action="START"
            intent.putExtra("url",track.audio)
            context.startService(intent)
        }catch (ex:Exception){

        }

    }

}