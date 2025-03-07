package com.example.music_app.domain.model


open class Topic(
    open val id:Int,
    open val name:String,
    open val tracks:List<Track>,
    open val image:String
) {

}