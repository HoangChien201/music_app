package com.example.music_app.core

import androidx.room.TypeConverter
import com.example.music_app.domain.model.Track
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<Track>?): String = Gson().toJson(value)

    @TypeConverter
    fun toTrackList(value: String) = Gson().fromJson(value, Array<Track>::class.java).toList()
}