package com.example.music_app.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.music_app.R
import com.example.music_app.component.TabBarSongPlay
import com.example.music_app.component.ToolbarCustom
import com.example.music_app.repository.ImageIcon
import com.example.music_app.ui.theme.Blue40
import com.example.music_app.ui.theme.Shapes

@Composable
fun SongPlayScreen(navController:NavHostController,songId:Int) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Blue40)
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
                .background(Color.White, shape = Shapes.extraLarge)
        ) {
            Column(
                modifier=Modifier.fillMaxWidth()
                    .weight(0.9f)
            ) {

            }
            Row(
                modifier=Modifier.fillMaxWidth()
                    .weight(0.1f)
                    .background(Blue40, shape = Shapes.extraLarge)
            ) {
                TabBarSongPlay()
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