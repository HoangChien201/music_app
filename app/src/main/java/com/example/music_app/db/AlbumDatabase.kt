package com.example.music_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.music_app.db.dao.AlbumDao
import com.example.music_app.domain.model.Album

@Database(entities=[Album::class], version = 1)
abstract class AlbumDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}