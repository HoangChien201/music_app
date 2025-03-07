package com.example.music_app.domain.repository

import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Track

interface TrackRepo {
    suspend fun getTrack(id: Int): BaseListResp<Track>
    suspend fun getTracks(): BaseListResp<Track>

}