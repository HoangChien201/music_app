package com.example.music_app.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.ui.components.HeadingCategoryDetail
import com.example.music_app.ui.components.SongItem
import com.example.music_app.ui.components.ToolbarCustom
import com.example.music_app.ui.components.ImageIcon
import com.example.music_app.repository.SongRepository
import com.example.music_app.ui.theme.BackGround
import com.example.music_app.ui.theme.Shapes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryDetailScreen(navController:NavHostController,category:Int) {

    Log.d("AAA", "category ${category}")
    val listSong = SongRepository.songs.filter { it -> it.category.id == category }
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
                    HeadingCategoryDetail(navController)
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
                                items(listSong) { it ->
                                    SongItem(navController,it)
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }



                }
//                items(listSong) { it ->
//                    SongItem(navController,it)
//                }
            }
    }


