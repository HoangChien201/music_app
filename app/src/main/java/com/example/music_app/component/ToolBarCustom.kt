package com.example.music_app.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.music_app.R
import com.example.music_app.repository.ImageIcon

@Composable
fun ToolbarCustom(turnBack:Boolean,navController:NavHostController?,items:List<ImageIcon>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(20.dp, 0.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        if (turnBack){
            ImageIconComponent(ImageIcon(
                R.drawable.arrow_back_24,
                "back",
                {navController?.popBackStack()}))
        }
        Row {
            items.forEach({it ->
                ImageIconComponent(it)
            })
        }



    }
}
