package com.example.music_app.presentation.track_play.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.music_app.domain.model.Track
import com.example.music_app.presentation.core.TracksCompose
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackBottomSheetModal(
    navController: NavHostController,
    trackPlaying:Track?,
    viewModel: TrackPlayViewModel
){
    //bottomsheet
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val isShowSheet by viewModel.showSheet.collectAsState(false)

    val tracksNext by viewModel.tracksNext.collectAsState(emptyList())
    val trackCurrentIndex= remember { derivedStateOf { tracksNext.indexOf(trackPlaying); } }.value

    val tracksPrev=remember { mutableStateOf(emptyList<Track>()) }
    if(trackCurrentIndex !=-1){
        tracksPrev.value=tracksNext.subList(0,trackCurrentIndex+1)
    }
    ModalBottomSheet(
        onDismissRequest = {
            viewModel.relateStateBottomSheet()
        },
        sheetState = sheetState,
    ) {
        Column(
            modifier =
            Modifier.background(Color.Black.copy(alpha = 0.9f))
                .fillMaxWidth()
                .fillMaxHeight()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Heading("Danh sách phát")
                TracksCompose(navController,tracksPrev.value,trackPlaying)
            }

            Column {
                Heading("Phát tiếp theo")
                TracksCompose(
                    navController,
                    tracksNext.subList(trackCurrentIndex+1,tracksNext.size).filter { it ->it.id!=trackPlaying?.id },
                    trackPlaying)
            }
        }
    }
}

@Composable
fun Heading(text:String){
    Text(
        text=text,
        color = Color.White,
        modifier=Modifier.padding(8.dp,8.dp),
        fontWeight = FontWeight.Bold,
        fontSize = androidx.compose.material3.MaterialTheme.typography.titleLarge.fontSize
    )
}