package com.example.music_app.presentation.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.music_app.R

@Composable
fun ToolbarCustom(turnBack:Boolean,navController:NavHostController?,items:List<ImageIcon>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp, 0.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        if (turnBack){
            ImageIconComponent(
                ImageIcon(
                R.drawable.arrow_back_w_24,
                "back",
                {navController?.popBackStack()})
            )
        }
        Row {
            items.forEach({it ->
                ImageIconComponent(it)
            })
        }



    }
}
