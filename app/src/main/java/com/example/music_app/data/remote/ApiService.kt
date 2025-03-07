package com.example.music_app.data.remote

import com.example.music_app.core.BuildConfig
import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Track
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tracks")
    suspend fun getTrack(
        @Query("id") trackId: String,
        @Query("client_id") clientId: String = BuildConfig.client_id,
        @Query("format") format: String = "jsonpretty"): BaseListResp<Track>

    @GET("/tracks/?client_id=16ed3b13&format=jsonpretty&order=popularity_month")
    suspend fun getTracks(): BaseListResp<Track>
}