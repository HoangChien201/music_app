package com.example.music_app.data.repoImp

import com.example.music_app.data.remote.ApiService
import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo

class TrackRepoImp(private val apiService: ApiService): TrackRepo {
    override suspend fun getTrack(id: String): BaseListResp<Track> {
        return apiService.getTrack(id)
    }

    override suspend fun getTracks(): BaseListResp<Track> {
        return apiService.getTracks()
    }
}