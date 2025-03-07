package com.example.music_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.music_app.domain.model.Track

@Dao
interface TrackDao{
    @Query("SELECT * FROM track")
    fun getAll(): List<Track>

    @Query("SELECT * FROM track WHERE id = :id")
    suspend fun getById(id: Int): Track

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertTrack(vararg tracks: Track)

}