package com.example.music_app.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music_app.domain.model.Album
import com.example.music_app.domain.usecase.CreateAlbumUseCase
import com.example.music_app.domain.usecase.CreateTrackUseCase
import com.example.music_app.domain.usecase.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private var getAlbumsUseCase: GetAlbumsUseCase,
    private var createAlbumUseCase: CreateAlbumUseCase,
    private var createTrackUseCase: CreateTrackUseCase
):ViewModel(){
    private val _albums= MutableStateFlow<List<Album>>(emptyList())
    val albumsStateFlow: StateFlow<List<Album>> get()= _albums

    init {
        viewModelScope.launch() {
            val albums=getAlbumsUseCase.invoke()
            albums.forEach { item ->
                createAlbumUseCase.invoke(item)
                viewModelScope.launch(Dispatchers.IO) {

                    item.tracks.forEach{
                        createTrackUseCase.invoke(it)
                    }
                }

            }
            _albums.value=albums
        }
    }
}