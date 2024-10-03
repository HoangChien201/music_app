package com.example.music_app.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.music_app.repository.SongRepository

@Composable
fun ListSong(navHostController: NavHostController,categoryId: Int){
    val listSong = SongRepository.songs.filter { it -> it.category.id == categoryId }
    Log.d("AAA", "categoryId ${categoryId}")

    LazyColumn(
        modifier = Modifier.padding(0.dp,20.dp,0.dp,0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        userScrollEnabled = false
    ) {
        items(listSong) { it ->
            SongItem(navHostController,it)
        }
    }
}

//@Preview
//@Composable
//fun ListSongPreview(){
//    ListSong()
//}