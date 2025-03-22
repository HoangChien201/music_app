package com.example.music_app.domain.model

data class HeaderResp (
    val status:String,
    val code:Int,
    val error_message:String,
    val warnings:String,
    val result:Int,
    val result_count:Int,
    val next:String
)
