package com.example.music_app.data.repoImp

import android.util.Log
import com.example.music_app.data.remote.ApiService
import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.db.dao.AlbumDao
import com.example.music_app.domain.model.Album
import com.example.music_app.domain.repository.AlbumRepo

class AlbumRepoImp(
    private val apiService: ApiService,
    private val dao: AlbumDao
): AlbumRepo {
    override suspend fun getAlbums():List<Album>{
        val albums = dao.getAll()
        if (albums.isNotEmpty()) {
            return albums
        }

        return apiService.getAlbums().results
    }
    override suspend fun getAlbum(id: Int): Album {
        return dao.getById(id)
    }

    override suspend fun insertAlbum(albums: Album) {
        dao.insertAlbum(albums)
    }
}