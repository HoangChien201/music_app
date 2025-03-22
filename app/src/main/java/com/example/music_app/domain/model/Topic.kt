package com.example.music_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.music_app.core.Converters
import com.google.gson.annotations.Expose


open class Topic(
    @Expose open val id:String,
    open val name:String,
    open val tracks:List<Track>,
    open val image:String
) {

}