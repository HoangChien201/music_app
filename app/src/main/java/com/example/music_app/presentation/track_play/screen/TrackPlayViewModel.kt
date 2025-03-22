package com.example.music_app.presentation.track_play.screen

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo
import com.example.music_app.domain.usecase.GetTrackUseCase
import com.example.music_app.domain.usecase.GetTracksUseCase
import com.example.music_app.factory.TrackService
import com.example.music_app.presentation.track_play.components.ActionType
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@SuppressLint("StaticFieldLeak")
class TrackPlayViewModel @Inject constructor(
    private var getTrackUseCase: GetTrackUseCase,
    private var getTracksUseCase: GetTracksUseCase,
    @ApplicationContext private var context: Context,
    savedStateHandle: SavedStateHandle,
    trackRepo: TrackRepo
): ViewModel(){
    private var isBound=false
    private var trackService:TrackService?=null
    var showSheet = MutableStateFlow<Boolean>(false)

    private val trackId: String = savedStateHandle["trackId"] ?: ""

    val track = trackRepo.trackCurrentState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        null
    )

    val isPlaying=MutableStateFlow<Boolean>(false)

    val tracksNext = trackRepo.tracksNextState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
    )

    private val _totalTime= MutableStateFlow(0)
    val totalTime: StateFlow<Int> get()= _totalTime

    private val currentPosition= MutableStateFlow(0)
    val currentPositionState: StateFlow<Int> get()= currentPosition.asStateFlow()

    private var serviceConnection=object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TrackService.TrackServiceBinder
            trackService = binder.getService()
            isBound=true
            handleServiceBound()

        }
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound=false
        }

    }

    private fun handleServiceBound(){
        viewModelScope.launch(Dispatchers.IO) {
            trackService?.currentPositionService?.collectLatest {
                currentPosition.value=it
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if(trackId.isNotEmpty()) {
                onEvent(ActionType.Start(trackId.toInt()))
            }
            //insert tracks in list track next
            trackRepo.insertTracksNextState(getTracksUseCase())

            Intent(context,TrackService::class.java).apply {
                context.bindService(this,serviceConnection,Context.BIND_AUTO_CREATE)
            }
        }

    }
    fun onEvent(action: ActionType){
        when(action){
            is ActionType.Play -> play()
            is ActionType.Pause -> pause()
            is ActionType.Stop -> stop()
            is ActionType.Next -> next()
            is ActionType.Previous -> previous()
            is ActionType.Start -> start(action.value)

        }
    }

    private fun previous() {
        Intent(context,TrackService::class.java).apply {
            action="PREVIOUS"
            context.startService(this)
        }
    }

    private fun next() {
        Intent(context,TrackService::class.java).apply {
            action="NEXT"
            context.startService(this)
        }
    }

    private fun stop() {
        TODO("Not yet implemented")
    }

    private fun pause() {
        isPlaying.value=false
        Intent(context,TrackService::class.java).apply {
            action="PAUSE"
            context.startService(this)
        }
    }

    private fun play() {
        isPlaying.value=true
        Intent(context, TrackService::class.java).apply {
            action="PLAY"
            context.startService(this)
        }
    }

    private fun start(trackID:Int){

        try {
            viewModelScope.launch(Dispatchers.IO) {
                val track=getTrackUseCase(trackID)

                _totalTime.value=track.duration.toInt()

                isPlaying.value=true

                delay(500)

                val gson = Gson()
                val trackJson = gson.toJson(track)
               Intent(context, TrackService::class.java).apply {
                   action="START"
                   putExtra("track_json",trackJson)
                   context.startService(this)
               }


            }

        }catch (ex:Exception){
            Log.d("TrackPlayViewModel", "Start: ${ex.message}")
        }

    }

    override fun onCleared() {
        super.onCleared()
        if(isBound){
            context.unbindService(serviceConnection)
        }
    }

    fun relateStateBottomSheet(){
        showSheet.value=!showSheet.value
    }

}
