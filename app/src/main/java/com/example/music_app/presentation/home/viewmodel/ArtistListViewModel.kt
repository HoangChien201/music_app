package com.example.music_app.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.usecase.CreateArtistUseCase
import com.example.music_app.domain.usecase.CreateTrackUseCase
import com.example.music_app.domain.usecase.GetArtistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistListViewModel @Inject constructor(
    private var getArtistsUseCase: GetArtistsUseCase,
    private var createArtistUseCase: CreateArtistUseCase,
    private var createTrackUseCase: CreateTrackUseCase
):ViewModel() {
    private val _artists= MutableStateFlow<List<Artist>>(emptyList())
    val artistsStateFlow: StateFlow<List<Artist>> get()= _artists

    init {
        viewModelScope.launch() {
            val artists=getArtistsUseCase.invoke()
            artists.forEach { item ->
                createArtistUseCase.invoke(item)
                viewModelScope.launch(Dispatchers.IO) {
                    item.tracks.forEach{
                        createTrackUseCase.invoke(it)
                    }
                }
            }
            _artists.value=artists
        }
    }

}