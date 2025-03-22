package com.example.music_app.presentation.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.music_app.presentation.home.components.AlbumItem

@Composable
fun <T> ListPopular(
    navHostController: NavHostController,
    title:String,
    list:List<T>,
    item: @Composable (navHostController: NavHostController,item:T) -> Unit
){
    Column {
        Box(modifier = Modifier.padding(0.dp, 15.dp)){
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(600)
            )
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            items(list) { it ->
                item(navHostController,it)
            }
        }

    }
}