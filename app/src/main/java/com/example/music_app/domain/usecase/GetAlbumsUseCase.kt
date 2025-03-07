package com.example.music_app.domain.usecase

import com.example.music_app.domain.repository.AlbumRepo

class GetAlbumsUseCase(private val albumsRepo: AlbumRepo)  {
}