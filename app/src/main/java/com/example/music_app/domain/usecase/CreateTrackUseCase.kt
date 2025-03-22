package com.example.music_app.domain.usecase

import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo
import javax.inject.Inject

class CreateTrackUseCase @Inject constructor(
    private var trackRepo: TrackRepo
){
    suspend fun invoke(track: Track) {
        trackRepo.insertTrack(track)
    }
}