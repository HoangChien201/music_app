package com.example.music_app.domain.usecase

import com.example.music_app.data.remote.respon.BaseListResp
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.repository.TrackRepo
import javax.inject.Inject

class GetTrackUseCase @Inject constructor(private val trackRepo: TrackRepo) {
    suspend operator fun invoke(id: Int): Track {
        var track=trackRepo.getTrack(id).results[0]
        return trackRepo.getTrack(id).results[0]
    }

}