package com.example.music_app.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.component.HeadingCategoryDetail
import com.example.music_app.component.SongItem
import com.example.music_app.component.ToolbarCustom
import com.example.music_app.repository.ImageIcon
import com.example.music_app.repository.SongRepository

@Composable
fun CategoryDetailScreen(navController:NavHostController,category:Int) {

    Log.d("AAA", "category ${category}")
    val listSong = SongRepository.songs.filter { it -> it.category.id == category }
    val scrollState = rememberScrollState()

    Box {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .padding(0.dp, 16.dp)
        ) {
            val faveriteToolbar= ImageIcon(R.drawable.heart_unactive, contentDescription = "faverite", action = {})

            ToolbarCustom(turnBack = true, navController = navController, items = listOf(faveriteToolbar))
        }

        Box {
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .fillMaxWidth() ,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                item {
                    HeadingCategoryDetail(navController)
                }
                items(listSong) { it ->
                    SongItem(navController,it)
                }
            }
        }

    }

}