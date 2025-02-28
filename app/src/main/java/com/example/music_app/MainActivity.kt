package com.example.music_app

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.music_app.destination.CategoryDestination
import com.example.music_app.destination.HomeDestination
import com.example.music_app.destination.SongDestination
import com.example.music_app.ui.screen.CategoryDetailScreen
import com.example.music_app.ui.screen.HomeScreen
import com.example.music_app.ui.screen.SongPlayScreen
import com.example.music_app.ui.theme.Music_AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Music_AppTheme {
                // A surface container using the 'background' color from the theme
                val navController= rememberNavController()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                }
                NavHost(navController = navController, startDestination = HomeDestination.route ){
                    composable(HomeDestination.route) {
                        HomeScreen(navController)
                    }

                    composable(
                        CategoryDestination.route + "/{${CategoryDestination.categoryId}}",
                        arguments = listOf(navArgument(CategoryDestination.categoryId) { type = NavType.IntType })
                    ) {
                        val categoryId= requireNotNull(it.arguments?.getInt(CategoryDestination.categoryId)){"Category id is null"}
                        CategoryDetailScreen(navController,categoryId)
                    }

                    composable(
                        SongDestination.route + "/{${SongDestination.songId}}",
                        arguments = listOf(navArgument(SongDestination.songId) { type = NavType.IntType })
                        ){
                        val songId= requireNotNull(it.arguments?.getInt(SongDestination.songId)){"Song id is null"}
                        SongPlayScreen(navController,songId)
                    }
                }
            }
        }
    }
}

