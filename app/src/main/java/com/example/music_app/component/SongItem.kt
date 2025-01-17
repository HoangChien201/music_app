package com.example.music_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.SongDestination
import com.example.music_app.repository.Song
import com.example.music_app.ui.theme.GrayText
import com.example.music_app.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongItem(navHostController: NavHostController,song:Song){
    Card(onClick = {
                   navHostController.navigate(SongDestination.route + "/${song.id}")
    },
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Box(modifier = Modifier
                .width(78.dp)
                .height(68.dp)
                .padding(0.dp, 0.dp, 10.dp, 0.dp),
                ){
                Image(
                    painter = painterResource(id = song.image),
                    contentDescription = "song item",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Box(modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)){
                    //name song
                    Text(
                        text = song.name,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    )
                }
                //name author
                Text(
                    text = song.author.fullname,
                    fontWeight = FontWeight(300),
                    color = GrayText,
                    fontSize = 12.sp)
            }
            Box(modifier = Modifier.padding(20.dp)){
                Image(
                    painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription ="option",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .clickable {

                        })
            }

                    


        }
    }
}

//@Preview
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SongItemPreview(){
//    SongItem()
//}