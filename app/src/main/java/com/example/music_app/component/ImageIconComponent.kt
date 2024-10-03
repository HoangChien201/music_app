package com.example.music_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.music_app.repository.ImageIcon

@Composable
fun ImageIconComponent(item:ImageIcon){
    Box(modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)){
        IconButton(
            onClick =  item.action ,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = item.image) ,
                contentDescription = item.contentDescription,
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)

            )
        }

    }
}