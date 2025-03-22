package com.example.music_app.domain.usecase

import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.repository.ArtistRepo
import javax.inject.Inject

class CreateArtistUseCase @Inject constructor(
    private var artistRepo: ArtistRepo
) {
    suspend fun invoke(artist: Artist) =artistRepo.insertArtist(artist)
}