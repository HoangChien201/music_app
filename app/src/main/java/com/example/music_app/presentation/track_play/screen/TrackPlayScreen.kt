package com.example.music_app.presentation.track_play.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.domain.model.Track
import com.example.music_app.presentation.track_play.components.TabBarSongPlay
import com.example.music_app.presentation.core.ToolbarCustom
import com.example.music_app.presentation.track_play.components.ControllerSong
import com.example.music_app.presentation.track_play.components.PlayerImage
import com.example.music_app.presentation.track_play.components.ProcessBar
import com.example.music_app.presentation.track_play.components.TextContent
import com.example.music_app.presentation.track_play.components.TrackBottomSheetModal
import com.example.music_app.presentation.core.ImageIcon
import com.example.music_app.presentation.theme.BackGround
import com.example.music_app.presentation.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackPlayScreen(
    navController:NavHostController,
    viewModel: TrackPlayViewModel= hiltViewModel(),
    ) {
    var isShuffle = remember { mutableStateOf(false) }
    var isFavarite = remember { mutableStateOf(false) }
    val track :Track ? by viewModel.track.collectAsState()
    val isShowSheetState by viewModel.showSheet.collectAsState(false)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(BackGround)
    ) {
        if(isShowSheetState){
            TrackBottomSheetModal(navController,track, viewModel = viewModel)
        }
        Box(
            modifier = Modifier
                .zIndex(1f)
                .padding(0.dp, 16.dp)
        ) {
            val downloadToolbar= ImageIcon(R.drawable.download_24, contentDescription = "download", action = {})

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
                if(track == null){
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                    return
                }
                PlayerImage(track?.image)
                TextContent(track?.name,track?.artist_name)
                ControllerSong()


                ProcessBar({})

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

