package com.example.music_app.domain.repository

import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Artist

interface ArtistRepo {
    suspend fun getArtists(): List<Artist>
    suspend fun getArtist(id: Int): Artist
    suspend fun insertArtist(artists: Artist)
}