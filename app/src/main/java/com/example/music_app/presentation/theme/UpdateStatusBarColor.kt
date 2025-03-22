package com.example.music_app.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun UpdateStatusBarColor(color:Color?=null){
    val systemUiController= rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color=Color.Transparent,darkIcons = false
    )
}