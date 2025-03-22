package com.example.music_app.domain.usecase

import com.example.music_app.domain.repository.ArtistRepo
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(
    private val artistRepo: ArtistRepo
) {
    suspend fun invoke(id:Int)=artistRepo.getArtist(id)
}