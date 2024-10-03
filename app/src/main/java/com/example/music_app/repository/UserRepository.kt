package com.example.music_app.repository

data class User(
    val id:Int,
    val fullname:String,
    val role:Int,
    val email:String,
    val wallet:Float,
    val avatar:String
)