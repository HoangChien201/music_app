package com.example.music_app.domain.repository

import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Album

interface AlbumRepo {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(id: Int): Album
    suspend fun insertAlbum(albums: Album)

}