package com.example.music_app.presentation.topic_detail.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.domain.model.AlbumsRepository
import com.example.music_app.domain.model.ArtistRepository
import com.example.music_app.domain.model.Track
import com.example.music_app.presentation.topic_detail.components.HeadingCategoryDetail
import com.example.music_app.presentation.topic_detail.components.TrackItem
import com.example.music_app.ui.components.ToolbarCustom
import com.example.music_app.ui.components.ImageIcon
import com.example.music_app.ui.theme.BackGround
import com.example.music_app.ui.theme.Shapes

sealed class CategoryType(val value:String){
    object Albums:CategoryType("albums")
    object Artist:CategoryType("artist")
    companion object {
        fun fromValue(value: String): CategoryType? {
            return when (value) {
                Albums.value -> Albums
                Artist.value -> Artist
                else -> null
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicDetailScreen(navController:NavHostController,topicId:String,category:String) {
    var tracks= listOf<Track>()
    var _category=CategoryType.fromValue(category)
    var topic=when(_category){
        is CategoryType.Artist -> ArtistRepository.artists.find { it->it.id==topicId }!! //goi usecase getTracksArtist
        is CategoryType.Albums-> AlbumsRepository.albums.find { it->it.id==topicId }!! //goi usecase getTracksAlbums
        null -> TODO()
    }
    when(_category){
        is CategoryType.Artist -> tracks = ArtistRepository.artists.find { it->it.id==topicId }!!.tracks //goi usecase getTracksArtist
        is CategoryType.Albums-> tracks = AlbumsRepository.albums.find { it->it.id==topicId }!!.tracks //goi usecase getTracksAlbums
        null -> TODO()
    }
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            Box(
            modifier = Modifier
                .zIndex(1f)
                .padding(0.dp, 16.dp)
        ) {
            val faveriteToolbar= ImageIcon(R.drawable.heart_unactive, contentDescription = "faverite", action = {})

            ToolbarCustom(turnBack = true, navController = navController, items = listOf(faveriteToolbar))
        }}
    ) { innerPadding ->

            Column(
                modifier = Modifier
                    .background(BackGround)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    HeadingCategoryDetail(navController, topic )
                            LazyColumn(
                                modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .wrapContentHeight()
                                .padding(0.dp,8.dp)
                                .background(Color.White.copy(alpha = 0.1f), shape = Shapes.large),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            )

                            {
                                item { Spacer(modifier = Modifier.height(16.dp)) }
                                items(tracks) { it ->
                                    TrackItem(navController,it,category,topicId)
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }



                }
//                items(listSong) { it ->
//                    SongItem(navController,it)
//                }
            }
    }


