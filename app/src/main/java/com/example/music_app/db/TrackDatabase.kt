package com.example.music_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.music_app.db.dao.TrackDao
import com.example.music_app.domain.model.Track

@Database(entities = [Track::class], version = 1)
abstract class TrackDatabase: RoomDatabase() {
    abstract fun trackDao(): TrackDao

}