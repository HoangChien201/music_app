package com.example.music_app.presentation.track_play.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.music_app.R
import com.example.music_app.domain.model.AlbumsRepository
import com.example.music_app.domain.model.ArtistRepository
import com.example.music_app.presentation.track_play.components.TabBarSongPlay
import com.example.music_app.ui.components.ToolbarCustom
import com.example.music_app.presentation.track_play.components.ControllerSong
import com.example.music_app.presentation.track_play.components.PlayerImage
import com.example.music_app.presentation.track_play.components.ProcessBar
import com.example.music_app.presentation.track_play.components.TextContent
import com.example.music_app.ui.components.ImageIcon
import com.example.music_app.factory.TrackFactory
import com.example.music_app.factory.TrackFactory.getCurrentPosition
import com.example.music_app.factory.TrackType
import com.example.music_app.presentation.topic_detail.screen.CategoryType
import com.example.music_app.presentation.track_play.components.ActionType
import com.example.music_app.ui.theme.BackGround
import com.example.music_app.ui.theme.Shapes
import kotlinx.coroutines.delay

@Composable
fun TrackPlayScreen(
    navController:NavHostController,
    trackId:Int,
    viewModel: TrackPlayViewModel = hiltViewModel ()
    ) {
    var currentTime = remember { mutableStateOf(0)}
    var isShuffle = remember { mutableStateOf(false) }
    var isFavarite = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
//        while(!TrackFactory.stop){
//            if (TrackFactory.isPlaying){
//                currentTime.value=getCurrentPosition()
//            }
//            delay(1000)
//        }
        viewModel.onEvent(ActionType.Start(trackId))

    }



//    var tracks= listOf<Track>()
//    var _category= CategoryType.fromValue(category)
//
//    when(_category){
//        is CategoryType.Artist -> tracks = ArtistRepository.artists.find { it->it.id==topicId }!!.tracks //goi usecase getTracksArtist
//        is CategoryType.Albums-> tracks = AlbumsRepository.albums.find { it->it.id==topicId }!!.tracks //goi usecase getTracksAlbums
//        null -> TODO()
//    }
//    var track = tracks.find { it->it.id==trackId }
//    var trackSrc = TrackType.TrackUrlType(track!!.audio,track!!.id)
//
//    TrackFactory.initialize(LocalContext.current,trackSrc)
//
//    if(!TrackFactory.isPlaying){
//        TrackFactory.play()
//    }else if(track.id!=TrackFactory.trackCurrent.toString()){
//        TrackFactory.play()
//    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(BackGround)
    ) {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .padding(0.dp, 16.dp)
        ) {
            val downloadToolbar= ImageIcon(R.drawable.download_icon, contentDescription = "download", action = {})

            ToolbarCustom(turnBack = true, navController = navController, items = listOf(downloadToolbar))
        }
        Column(
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White.copy(alpha = 0.1f), shape = Shapes.extraLarge)
        ) {
            Column(
                modifier=Modifier.fillMaxWidth()
                    .weight(0.9f)
                    .padding(0.dp,62.dp,0.dp,0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
//                PlayerImage(track)
//
//                TextContent(track.name)
                ControllerSong()

//                ProcessBar({},TrackFactory.totalTime!!,currentTime.value.toFloat())

            }
            Row(
                modifier=Modifier.fillMaxWidth()
                    .weight(0.2f)
                    .background(BackGround, shape = Shapes.extraLarge)
            ) {
                TabBarSongPlay(isShuffle,isFavarite)
            }
        }
    }
}

