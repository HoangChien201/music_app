package com.example.music_app.ui.components.song_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.music_app.ui.theme.GrayText

@Composable
fun TextContent(nameSong: String, nameSinger: String) {
    Column(
        modifier = Modifier.padding(0.dp,24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //name song
        Text(
            text = nameSong,
            fontWeight = FontWeight(600),
            fontSize = 20.sp,
            color = Color.White

            )
        //name singer
        Text(
            text = nameSinger,
            fontWeight = FontWeight(450),
            fontSize = 16.sp,
            color = GrayText
            )
    }
}