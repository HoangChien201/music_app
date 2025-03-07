package com.example.music_app.presentation.home.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.presentation.home.components.ListAlbumsPopular
import com.example.music_app.presentation.home.components.ListArtistPopular
import com.example.music_app.ui.components.MusicNotification
import com.example.music_app.presentation.home.components.SearchComponent
import com.example.music_app.ui.theme.BackGround
import com.example.music_app.ui.theme.UpdateStatusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController:NavHostController) {
    val scrollState = rememberScrollState()
    val songTitle = remember { "" }
    val artistName = remember { "Nghệ sĩ nổi tiếng" }
//    SongFactory.pause()
    MusicNotification()
    UpdateStatusBarColor()

    Scaffold(
        topBar = {
            if(songTitle.isNotEmpty() && artistName.isNotEmpty()){
                MusicStatusBar(songTitle, artistName) }
            }
    ) {  innerPadding ->
        Column(
            modifier = Modifier
                .background(BackGround)
                .padding(24.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .fillMaxSize()
                ,

            ) {

            Box(modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp),){
                HeadingHome()
            }

            SearchComponent()

            ListAlbumsPopular(navController)

            ListArtistPopular(navController)
        }
    }

}

@Composable
fun HeadingHome(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(0.dp,0.dp,16.dp,0.dp)){
            Image(
                painter = painterResource(id = R.drawable.logo_heading_home),
                contentDescription = "logo_heading",
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
            )
        }

        Text(
            text = "Xin chào",
            modifier = Modifier,
            color = Color.Black,
            fontWeight = FontWeight(500),
            fontSize = 20.sp
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicStatusBar(songTitle: String, artistName: String) {
    TopAppBar(
        title = {
            Column {
                Text(text = songTitle)
                Text(text = artistName, style = MaterialTheme.typography.bodySmall)
            }
        },
        actions = {
            // Thêm các nút để điều khiển âm nhạc
            IconButton(onClick = { /* Xử lý nút phát / dừng */ }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Play")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun HomeScreenPreview(){
    HeadingHome()
}

