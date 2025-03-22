package com.example.music_app.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.music_app.R
import com.example.music_app.destination.TopicDestination
import com.example.music_app.domain.model.Album
import com.example.music_app.domain.model.Artist
import com.example.music_app.domain.model.Topic
import com.example.music_app.presentation.theme.GrayText
import com.example.music_app.presentation.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumItem(navHostController: NavHostController,album: Album ){
    Card(
        onClick = {
            navHostController.navigate(TopicDestination.route +"/album"+ "/${album.id}")
        },
        modifier = Modifier
            .width(250.dp)
            .height(300.dp)
            .shadow(2.dp, Shapes.large)
            .offset(0.dp,-1.dp),
        shape = Shapes.large

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White.copy(alpha = 0.1f))
                .padding(0.dp,10.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(album.image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            )
            Column(
                Modifier.padding(16.dp,0.dp)
            ) {
                Box(modifier = Modifier.padding(0.dp,10.dp)){
                    Text(text="${album.tracks.size} bài hát ",
                        fontSize = 13.sp,)
                }
                //name category
                Text(
                    text = album.name,
                    fontSize = 22.sp,
                    color = GrayText,
                    fontWeight = FontWeight(500)
                    )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ArtistItem(navHostController: NavHostController,artist: Artist ){
    Card(
        onClick = {
            navHostController.navigate(TopicDestination.route +"/artist"+ "/${artist.id}")
        },
        modifier = Modifier
            .width(250.dp)
            .height(300.dp)
            .shadow(2.dp, Shapes.large)
            .offset(0.dp,-1.dp),
        shape = Shapes.large

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White.copy(alpha = 0.1f))
                .padding(0.dp,10.dp),
        ) {
            if(artist.image.isNotEmpty()){
                Image(
                    painter = rememberAsyncImagePainter(artist.image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                )
            }
            else{
                Image(
                    painter = painterResource(id = R.drawable.the_band_show),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                )

            }

            Column(
                Modifier.padding(16.dp,0.dp)
            ) {
                Box(modifier = Modifier.padding(0.dp,10.dp)){
                    Text(text="${artist.tracks.size} bài hát ",
                        fontSize = 13.sp,)
                }
                //name category
                Text(
                    text = artist.name,
                    fontSize = 22.sp,
                    color = GrayText,
                    fontWeight = FontWeight(500)
                )
            }

        }
    }
}