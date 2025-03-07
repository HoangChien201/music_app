package com.example.music_app.presentation.track_play.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.music_app.domain.model.Track

@Composable
fun PlayerImage(track: Track) {

        Image(
            painter = rememberAsyncImagePainter(track.image),
            contentDescription = "the band show",
            modifier = Modifier
                .width(231.dp)
                .height(231.dp)
                .clip(shape = CircleShape)
        )
}