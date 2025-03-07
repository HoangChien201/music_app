package com.example.music_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.music_app.destination.HomeDestination
import com.example.music_app.destination.TopicDestination
import com.example.music_app.destination.TrackDestination
import com.example.music_app.presentation.home.screen.HomeScreen
import com.example.music_app.presentation.topic_detail.screen.TopicDetailScreen
import com.example.music_app.presentation.track_play.screen.TrackPlayScreen
import com.example.music_app.presentation.track_play.screen.TrackPlayViewModel
import com.example.music_app.ui.theme.Music_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                        TopicDestination.route +"/{${TopicDestination.category}}"+ "/{${TopicDestination.topicId}}",
                        arguments = listOf(navArgument(TopicDestination.topicId) { type = NavType.StringType })
                    ) {
                        val category = requireNotNull(it.arguments?.getString(TopicDestination.category)){"Category is null"}
                        val topicId= requireNotNull(it.arguments?.getString(TopicDestination.topicId)){"Topic id is null"}
                        TopicDetailScreen(navController,topicId,category)
                    }

                    composable(
                        TrackDestination.route + "/{${TrackDestination.trackId}}",
                        arguments = listOf(navArgument(TrackDestination.trackId) { type = NavType.StringType })
                        ){
                        val trackId= requireNotNull(it.arguments?.getString(TrackDestination.trackId)){"Song id is null"}
                        TrackPlayScreen(navController, trackId.toInt())
                    }
                }
            }
        }
    }
}

