package com.example.music_app.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.music_app.domain.model.Track
import com.example.music_app.presentation.topic_detail.components.TrackItem
import com.example.music_app.presentation.theme.Shapes

@Composable
fun TracksCompose(
    navController: NavHostController,
    tracks: List<Track>,
    trackPlaying:Track?=null,
    background:Boolean=false) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp,8.dp)
            .background( if(background) Color.White.copy(alpha = 0.1f) else Color.Transparent,
                shape = Shapes.large),
        horizontalAlignment = Alignment.CenterHorizontally,
    )

    {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        items(tracks) { it ->
            var active=trackPlaying?.id == it.id
            TrackItem(navController,it, active)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}