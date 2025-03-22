package com.example.music_app.domain.usecase

import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.repository.ArtistRepo
import javax.inject.Inject

class GetArtistsUseCase @Inject
    constructor(
        private val artistRepo: ArtistRepo,
    ) {
    suspend fun invoke(): List<Artist> {
        return artistRepo.getArtists()
    }
}