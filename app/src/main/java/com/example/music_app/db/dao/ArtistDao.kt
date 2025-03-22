package com.example.music_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.music_app.domain.model.Artist

@Dao
interface ArtistDao{
    @Query("SELECT * FROM artist")
    suspend fun getAll(): List<Artist>

    @Query("SELECT * FROM artist WHERE id = :id")
    suspend fun getById(id: Int): Artist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(vararg artists: Artist)

}