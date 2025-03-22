package com.example.music_app.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music_app.R
import com.example.music_app.presentation.theme.Shapes

@Composable
fun SearchComponent() {
    fun onValueChange(value: String) {

    }

    var text by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Brush.verticalGradient(listOf(Color(0xFF221536), Color(0xFF524C64))), shape = Shapes.large),
        value = text,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "search-icon",
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
            )
        },
        onValueChange = {
            text = it
        },
        placeholder = { Text(text = "Tìm kiếm bài hát", color = Color.White) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        )

    )
}


@Preview
@Composable
fun SearchComponentPreview(){
    SearchComponent()
}