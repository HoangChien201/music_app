package com.example.music_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.ui.theme.Blue40
import com.example.music_app.ui.theme.GrayText40

@Composable
fun HeadingCategoryDetail(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .background(
                Blue40,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
    ) {

        Image(painter = painterResource(id = R.drawable.the_band_show),
            contentDescription = "the band show",
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp))

        Column(
            modifier = Modifier.padding(30.dp,12.dp,0.dp,0.dp)
        ) {
            Text(
                text = "Những bài hát để hát lớn",
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color.Black
            )
            Text(
                text = "Hơn 30 bài hát để hát khi tắm",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = GrayText40
            )
        }
        Box(modifier = Modifier.padding(0.dp,20.dp))

    }
}
