package com.example.music_app.presentation.track_play.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import com.example.music_app.presentation.theme.GrayText
val THOUNDSAND_FACTOR=1000

@Composable
fun ProcessBar(
    onChaneValue:(Float)->Unit,
    viewModel: TrackPlayViewModel = hiltViewModel()
    ){
    var totalTime = viewModel.totalTime.collectAsState(0).value
    val currentPosition = viewModel.currentPositionState.collectAsState(0).value / THOUNDSAND_FACTOR

    Row(
        modifier = Modifier.padding(16.dp,40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        TimeLine( formatTime(currentPosition.toInt()))
        Slider(
            modifier = Modifier.weight(1f),
            colors = SliderDefaults.colors(
                thumbColor = Color.Black,
                activeTrackColor = Color.Black),
            value = currentPosition.toFloat(),
            valueRange = 0f..totalTime.toFloat(),
            onValueChange =onChaneValue,
            )
        TimeLine(formatTime(totalTime))


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

fun formatTime(timeInSeconds: Int): String {
    val minutes = timeInSeconds / 60
    val seconds = timeInSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}
fun formatedTime(ms: Int): String {
    var tim = ""
    var secStrng = ""
    var minStrng = ""
    val s = (ms % 60)
    val m = (ms / 60) % 60
    val h = ((ms / (60 * 60)) % 24)
    if (h > 0) {
        tim = "$h:"
    }
    secStrng = if (s < 10) {
        "0$s"
    } else {
        "" + s
    }
    minStrng = if (m < 10) {
        "0$m"
    } else {
        "" + m
    }
    tim = "$tim$minStrng:$secStrng"
    return tim
}