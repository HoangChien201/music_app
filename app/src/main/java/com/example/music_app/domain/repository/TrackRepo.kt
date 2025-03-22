package com.example.music_app.domain.repository

import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Track
import kotlinx.coroutines.flow.StateFlow

interface TrackRepo {
    val tracksNextState: StateFlow<List<Track>>
    val trackCurrentState: StateFlow<Track?>
    suspend fun getTrack(id: Int): Track
    suspend fun getTracks(): List<Track>
    suspend fun insertTrack( track: Track)
    fun changeTrackState(track: Track)
    fun removeTrackState(track: Track)
    suspend fun insertTracksNextState(tracks: List<Track>)
    fun insertTrackNextState(track: Track)


}