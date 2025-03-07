package com.example.music_app.presentation.topic_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.music_app.R
import com.example.music_app.domain.model.Topic
import com.example.music_app.ui.theme.GrayText40

@Composable
fun HeadingCategoryDetail(navHostController: NavHostController,topic: Topic) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth()
            .background(color=Color.Transparent)
            .padding(0.dp,40.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painter = rememberAsyncImagePainter(topic.image),
            contentDescription = "the band show",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        )

        Column(
            modifier =
            Modifier
                .padding(12.dp,0.dp)
            ,

        ) {
            Text(
                text = topic.name,
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color.Black
            )

        }

    }
}
