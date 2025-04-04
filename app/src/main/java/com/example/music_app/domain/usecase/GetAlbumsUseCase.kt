package com.example.music_app.domain.usecase

import com.example.music_app.domain.model.Album
import com.example.music_app.domain.repository.AlbumRepo
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumsRepo: AlbumRepo,
    ) {
    suspend fun invoke(): List<Album> {
        return  albumsRepo.getAlbums()
    }
}