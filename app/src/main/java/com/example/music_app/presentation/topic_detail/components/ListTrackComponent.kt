package com.example.music_app.presentation.topic_detail.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.music_app.domain.model.Track

@Composable
fun ListTrackComponent(navHostController: NavHostController,tracks:List<Track>){

    LazyColumn(
        modifier = Modifier.padding(0.dp,20.dp,0.dp,0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        userScrollEnabled = false
    ) {
        items(tracks) { it ->
//            TrackItem(navHostController,it)
        }
    }
}

//@Preview
//@Composable
//fun ListSongPreview(){
//    ListSong()
//}