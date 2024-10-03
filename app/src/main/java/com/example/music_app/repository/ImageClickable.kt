package com.example.music_app.repository

class ImageIcon(image:Int,contentDescription:String,action:()->Unit) {
    val image=image;
    val contentDescription=contentDescription;
    val action=action
}