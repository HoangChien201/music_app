package com.example.music_app.data.repoImp

import com.example.music_app.data.remote.ApiService
import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.db.dao.TrackDao
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class TrackRepoImp(
    private val apiService: ApiService,
    private val dao: TrackDao
): TrackRepo {

    private val _tracksNext= MutableStateFlow<List<Track>>(emptyList())
    override val tracksNextState:StateFlow<List<Track>> get()  = _tracksNext

    private val _trackCurrent= MutableStateFlow<Track?>(null)
    override val trackCurrentState:StateFlow<Track?> get()  = _trackCurrent

    override suspend fun getTrack(id: Int): Track {
        var track=dao.getById(id)
        if(track==null){
            return apiService.getTrack(id).results.get(0)
        }
        return track
    }

    override suspend fun getTracks(): List<Track> {
        return apiService.getTracks().results
    }

    override suspend fun insertTrack( track: Track) {
        dao.insertTrack(track)
    }

    override fun changeTrackState(track: Track){
        _trackCurrent.value=track

    }

    override fun removeTrackState(track: Track){
        _trackCurrent.value = null

    }

    override suspend fun insertTracksNextState(tracks: List<Track>) {
        _tracksNext.value=tracks
    }
    override fun insertTrackNextState(track: Track){
        if(_tracksNext.value.contains(track)) return
        _tracksNext.value= _tracksNext.value.toMutableList().apply { add(0,track) }
    }
}