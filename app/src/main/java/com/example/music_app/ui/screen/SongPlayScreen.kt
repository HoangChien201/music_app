package com.example.music_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.music_app.R
import com.example.music_app.ui.components.TabBarSongPlay
import com.example.music_app.ui.components.ToolbarCustom
import com.example.music_app.ui.components.song_detail.ControllerSong
import com.example.music_app.ui.components.song_detail.PlayerImage
import com.example.music_app.ui.components.song_detail.ProcessBar
import com.example.music_app.ui.components.song_detail.TextContent
import com.example.music_app.factory.SongFactory
import com.example.music_app.ui.components.ImageIcon
import com.example.music_app.factory.SongType
import com.example.music_app.ui.theme.BackGround
import com.example.music_app.ui.theme.Blue40
import com.example.music_app.ui.theme.Shapes

@Composable
fun SongPlayScreen(navController:NavHostController,songId:Int) {
    var currentTime = remember { mutableFloatStateOf(0f) }
    var isShuffle = remember { mutableStateOf(false) }
    var isFavarite = remember { mutableStateOf(false) }

//    var song=SongRepository.songs.filter { it -> it.id == songId }.get(0)
    var song = SongType.SongUrlType("https://prod-1.storage.jamendo.com/?trackid=887202&format=mp31&from=Hpu8GJy8ToIqcJ9%2B0B7mKw%3D%3D%7CaYYrt00otDsOJmLUrURRuA%3D%3D",887202)
    SongFactory.initialize(LocalContext.current,song)
//    Log.d("AAA", "songresource ${song.resource}")
//    Log.d("AAA", "songresourceId ${SongFactory.isPlaying}")
    if(!SongFactory.isPlaying){
        SongFactory.play()
    }else if(song.id!=SongFactory.songCurrent){
        SongFactory.play()
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(BackGround)
    ) {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .padding(0.dp, 16.dp)
        ) {
            val downloadToolbar= ImageIcon(R.drawable.download_icon, contentDescription = "download", action = {})

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
                PlayerImage()

//                TextContent(song.name,song.author)
                TextContent("OK","Author")
                ControllerSong()

                ProcessBar({},SongFactory.totalTime!!,currentTime.value)

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

@Preview
@Composable
fun SongPlayScreenPreview() {
    val navController= rememberNavController()
    SongPlayScreen(navController,1)
}