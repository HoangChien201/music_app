package com.example.music_app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.music_app.domain.model.Album

@Dao
interface AlbumDao{
    @Query("SELECT * FROM album")
    suspend fun getAll():List<Album>

    @Query("SELECT * FROM album WHERE id=:id")
    suspend fun getById(id:Int):Album

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(vararg albums: Album)

}