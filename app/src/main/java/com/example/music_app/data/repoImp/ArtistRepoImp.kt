package com.example.music_app.data.repoImp

import com.example.music_app.data.remote.ApiService
import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.db.dao.ArtistDao
import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.repository.ArtistRepo

class ArtistRepoImp(
    private val apiService: ApiService,
    private val dao: ArtistDao
): ArtistRepo {
    override suspend fun getArtists(): List<Artist> {
        val artists = dao.getAll()
        if (artists.isNotEmpty()) {
            return artists
        }
        return apiService.getArtists().results
    }

    override suspend fun getArtist(id: Int): Artist {
        return dao.getById(id)
    }

    override suspend fun insertArtist(artists: Artist) {
        dao.insertArtist(artists)
    }
}