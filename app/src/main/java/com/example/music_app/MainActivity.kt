package com.example.music_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.music_app.screen.CategoryDetailScreen
import com.example.music_app.screen.HomeScreen
import com.example.music_app.screen.SongPlayScreen
import com.example.music_app.ui.theme.Music_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Music_AppTheme {
                // A surface container using the 'background' color from the theme
                val navController= rememberNavController()
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

