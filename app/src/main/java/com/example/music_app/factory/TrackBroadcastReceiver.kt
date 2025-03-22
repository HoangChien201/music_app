package com.example.music_app.factory

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.music_app.di.MusicApplication
import com.example.music_app.presentation.track_play.components.ActionType
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import javax.inject.Inject

class TrackBroadcastReceiver :BroadcastReceiver(){



    override fun onReceive(p0: Context?, p1: Intent?) {

    }

}