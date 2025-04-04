package com.example.music_app.domain.usecase

import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo
import javax.inject.Inject

class GetTracksUseCase @Inject constructor(
    private val trackRepo: TrackRepo
)  {
    suspend operator fun invoke(): List<Track> {
        return trackRepo.getTracks()
    }
}