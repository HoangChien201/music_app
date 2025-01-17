package com.example.music_app.component

import android.util.Log
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
import com.example.music_app.CategoryDestination
import com.example.music_app.R
import com.example.music_app.repository.Category
import com.example.music_app.ui.theme.GrayText
import com.example.music_app.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(navHostController: NavHostController,category: Category){
    Card(
        onClick = {
            navHostController.navigate(CategoryDestination.route + "/${category.id}")
        },
        modifier = Modifier
            .width(250.dp)
            .height(280.dp)
            .shadow(2.dp, Shapes.large)
            .offset(0.dp,-1.dp),
        shape = Shapes.large

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(id = R.drawable.classic_category_image),
                contentDescription = "Classisc Hits",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Column(
                Modifier.padding(16.dp,0.dp)
            ) {
                Box(modifier = Modifier.padding(0.dp,10.dp)){
                    Text(text="30 songs for an acoustis afternoon",
                        fontSize = 13.sp,)
                }
                //name category
                Text(
                    text = category.name,
                    fontSize = 22.sp,
                    color = GrayText,
                    fontWeight = FontWeight(500)
                    )
            }

        }
    }
}

//@Preview
//@Composable
//fun CategoryItemPreview(){
//    CategoryItem()
//}