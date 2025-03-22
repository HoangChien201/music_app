package com.example.music_app.domain.usecase

import com.example.music_app.domain.model.Album
import com.example.music_app.domain.repository.AlbumRepo
import javax.inject.Inject

class CreateAlbumUseCase @Inject constructor(
    private var albumRepo: AlbumRepo
){
    suspend fun invoke(album: Album) =albumRepo.insertAlbum(album)
}