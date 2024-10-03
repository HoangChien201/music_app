package com.example.music_app.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.music_app.R
import com.example.music_app.repository.ImageIcon

@Composable
fun TabBarSongPlay(){
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ImageIconComponent(item = ImageIcon(R.drawable.heart_unactive,"heart", action = {}))
        ImageIconComponent(item = ImageIcon(R.drawable.playlist_unactive,"playlist",action={}))
        ImageIconComponent(item = ImageIcon(R.drawable.shuffle_active,"shuffle",action={}))
    }
}