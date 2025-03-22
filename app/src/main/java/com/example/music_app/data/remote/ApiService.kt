package com.example.music_app.data.remote

import com.example.music_app.core.BuildConfig
import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Album
import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.model.Track
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("tracks")
    suspend fun getTrack(
        @Query("id") trackId: Int,
        @Query("client_id") clientId: String = BuildConfig.client_id,
        @Query("format") format: String = "jsonpretty"): BaseListResp<Track>

    @GET("tracks")
    suspend fun getTracks(
        @Query("client_id") clientId: String = BuildConfig.client_id,
        @Query("format") format: String = "jsonpretty",
        @Query("order") order: String = "popularity_month",
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 1
    ): BaseListResp<Track>

    @GET("albums/tracks")
    suspend fun getAlbums(
        @Query("client_id") clientId: String = BuildConfig.client_id,
        @Query("format") format: String = "jsonpretty",
        @Query("order") order: String = "popularity_month"

    ): BaseListResp<Album>

    @GET("artists/tracks")
    suspend fun getArtists(
        @Query("client_id") clientId: String = BuildConfig.client_id,
        @Query("format") format: String = "jsonpretty",
        @Query("order") order: String = "popularity_month"

    ):BaseListResp<Artist>

}