package com.example.music_app.presentation.home.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.music_app.domain.model.Album
import com.example.music_app.domain.model.AlbumsRepository
import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.model.ArtistRepository
import com.example.music_app.presentation.core.ListPopular
import com.example.music_app.presentation.home.viewmodel.AlbumListViewModel
import com.example.music_app.presentation.home.viewmodel.ArtistListViewModel
import com.example.music_app.presentation.track_play.components.ActionType
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel

@Composable
fun AlbumsListPopular(
    navHostController: NavHostController,
    viewModel: AlbumListViewModel= hiltViewModel(),
    ){

    var albums=viewModel.albumsStateFlow.collectAsState(initial = emptyList()).value
    ListPopular(
        navHostController,
        item = {navHostController,Album->AlbumItem(navHostController,Album)},
        title = "Albums",
        list = albums
    )
}


@Composable
fun ArtistListPopular(
    navHostController: NavHostController,
    viewModel: ArtistListViewModel = hiltViewModel()
){

    var artists= viewModel.artistsStateFlow.collectAsState(initial = emptyList()).value
    ListPopular(
        navHostController,
        item = { navHostController,Artist -> ArtistItem(navHostController,Artist) },
        title = "Nghệ sĩ",
        list = artists
    )

}
