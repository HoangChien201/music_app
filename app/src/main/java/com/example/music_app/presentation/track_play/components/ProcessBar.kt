package com.example.music_app.presentation.track_play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.music_app.ui.theme.GrayText

@Composable
fun ProcessBar(onChaneValue:(Float)->Unit,totalTime:Int,currentTime:Float){
    var totalTime=totalTime/3600
    Row(
        modifier = Modifier.padding(16.dp,40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        TimeLine( 0.toString())
        Slider(
            modifier = Modifier.weight(1f),
            colors = SliderDefaults.colors(
                thumbColor = Color.Black,
                activeTrackColor = Color.Black),
            value = currentTime/3600,
            valueRange = 0f..totalTime.toFloat(),
            onValueChange =onChaneValue,
            )
        TimeLine(totalTime .toFloat().toString())

    }
}

@Composable
fun TimeLine(time:String){
    Text(
        text = time,
        modifier = Modifier.padding(8.dp,0.dp),
        color = GrayText,
        fontWeight = FontWeight(450),
        fontSize = 15.sp
    )
}