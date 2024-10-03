package com.example.music_app.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.component.CategoryItem
import com.example.music_app.component.ListCategoryPopular
import com.example.music_app.component.SearchComponent
import com.example.music_app.component.SongItem
import com.example.music_app.ui.theme.BackGround
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(BackGround)
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .fillMaxSize(),

    ) {

        Box(modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp),){
            HeadingHome()
        }

        SearchComponent()

        ListCategoryPopular(navController)


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

@Preview
@Composable
fun HomeScreenPreview(){
    HeadingHome()
}