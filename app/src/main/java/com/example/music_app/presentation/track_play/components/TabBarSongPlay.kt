package com.example.music_app.presentation.track_play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.music_app.R
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import com.example.music_app.presentation.core.ImageIcon
import com.example.music_app.presentation.core.ImageIconComponent

@Composable
fun TabBarSongPlay(
    isShuffle:MutableState<Boolean>,
    isFavarite:MutableState<Boolean>,
    viewModel: TrackPlayViewModel= hiltViewModel()
    ){
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageIconComponent(item = ImageIcon(
            if(isFavarite.value) R.drawable.heart_unactive else R.drawable.heart_active,
            "heart",
            action = {
            isFavarite.value=!isFavarite.value
        })
        )
        ImageIconComponent(item = ImageIcon(R.drawable.playlist_unactive,"playlist",action={
            viewModel.relateStateBottomSheet()
        }))
        ImageIconComponent(
            item = ImageIcon( if(isShuffle.value) R.drawable.shuffle_active else R.drawable.shuffle_unactive,
            "shuffle",
            action={
            isShuffle.value=!isShuffle.value
        })
        )
    }
}