package com.example.music_app.domain.usecase

import com.example.music_app.domain.repository.AlbumRepo
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val albumRepo: AlbumRepo
)  {
    suspend operator fun invoke(id:Int)=albumRepo.getAlbum(id)

}