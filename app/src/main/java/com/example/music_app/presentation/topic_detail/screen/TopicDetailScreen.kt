package com.example.music_app.presentation.topic_detail.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.presentation.core.TracksCompose
import com.example.music_app.presentation.topic_detail.components.HeadingCategoryDetail
import com.example.music_app.presentation.core.ToolbarCustom
import com.example.music_app.presentation.core.ImageIcon
import com.example.music_app.presentation.theme.BackGround


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicDetailScreen(
    navController:NavHostController,
    topicId:String,
    category:String,
    viewModel: TopicDetailViewModel= hiltViewModel()
) {

    LaunchedEffect(Unit) {
        val _category=CategoryType.fromValue(category)
        viewModel.getData(_category!!,topicId.toInt())
    }
    var tracks=viewModel.tracks.collectAsState(initial = emptyList()).value
    var image=viewModel.image.collectAsState().value
    var title=viewModel.title.collectAsState().value

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
                    HeadingCategoryDetail(navController, image,title )

                    TracksCompose(navController, tracks,background = true)

                }
            }
    }


