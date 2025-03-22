package com.example.music_app.presentation.track_play.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.music_app.R
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import com.example.music_app.presentation.theme.BackGround
import com.example.music_app.presentation.theme.GrayText

@Composable
fun ControllerSong(
    viewModel: TrackPlayViewModel = hiltViewModel()
){
    val playing by viewModel.isPlaying.collectAsState(false)

    fun pressIconPlay(){
        if(playing){
            viewModel.onEvent(ActionType.Pause)
        }
        else{
            viewModel.onEvent(ActionType.Play)
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(0.dp,24.dp)
    ) {
        IconButton(onClick = {
            viewModel.onEvent(ActionType.Previous)
        }) {
            Icon(
                painterResource(R.drawable.fast_rewind_24),
                contentDescription = "Favorite",
                tint = GrayText,
                modifier = Modifier.size(48.dp)
            )
        }
        Box(
            modifier = Modifier.padding(24.dp,0.dp)
                .width(80.dp)
                .height(80.dp)
                .background(BackGround, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {pressIconPlay()} ) {
                Icon(
                    painterResource(
                       if(playing) R.drawable.pause_24 else R.drawable.play_24),
                    contentDescription = "Favorite",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        IconButton(onClick = {
            viewModel.onEvent(ActionType.Next)
        }) {
            Icon(
                painterResource(R.drawable.fast_forward_24),
                contentDescription = "Favorite",
                tint = GrayText,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}