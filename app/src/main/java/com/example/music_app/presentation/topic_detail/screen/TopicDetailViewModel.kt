package com.example.music_app.presentation.topic_detail.screen

import androidx.lifecycle.ViewModel
import com.example.music_app.domain.model.Track
import com.example.music_app.domain.usecase.GetAlbumUseCase
import com.example.music_app.domain.usecase.GetArtistUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class CategoryType(val value:String){
    object Albums:CategoryType("album")
    object Artist:CategoryType("artist")
    companion object {
        fun fromValue(value: String): CategoryType? {
            return when (value) {
                Albums.value -> Albums
                Artist.value -> Artist
                else -> null
            }
        }
    }
}

@HiltViewModel
class TopicDetailViewModel
    @Inject constructor(
        val getArtistUseCase: GetArtistUseCase,
        val getAlbumUseCase: GetAlbumUseCase
    )
:ViewModel(){
    val _title= MutableStateFlow<String>("Đang tải...")
    val title:StateFlow<String> get() =_title

    val _image= MutableStateFlow<String>("")
    val image:StateFlow<String> get()=_image

    val _tracks= MutableStateFlow<List<Track>>(emptyList())
    val tracks:StateFlow<List<Track>> get()=_tracks

    suspend fun getData(catagoryType:CategoryType,id:Int){
        when(catagoryType){
            is CategoryType.Artist -> {
                _title.value=getArtistUseCase.invoke(id).name
                _image.value=getArtistUseCase.invoke(id).image
                _tracks.value=getArtistUseCase.invoke(id).tracks
            }
            is CategoryType.Albums -> {
                _title.value=getAlbumUseCase.invoke(id).name
                _image.value=getAlbumUseCase.invoke(id).image
                _tracks.value=getAlbumUseCase.invoke(id).tracks
            }


        }
    }
}