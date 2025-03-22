package com.example.music_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.music_app.db.dao.ArtistDao
import com.example.music_app.domain.model.Artist

@Database(entities=[Artist::class], version = 1)
abstract class ArtistDatabase:RoomDatabase() {
    abstract fun artistDao(): ArtistDao

}