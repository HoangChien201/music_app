package com.example.music_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Track(

    @PrimaryKey
    val id: Int,

    val position: String?=null,
    val name: String,
    val duration: String,
    val artist_id: String?=null,
    val artist_name: String?=null,
    val artist_idstr: String?=null,
    val album_id: String?=null,
    val album_name: String?=null,
    val releasedate: String?=null,
    val license_ccurl:String,
    val album_image:String?=null,
    val image:String?=null,
    val audio:String,
    val audiodownload:String,
    val prourl:String?=null,
    val shorturl:String?=null,
    val shareurl:String?=null,
    val waveform:String?=null,
    val audiodownload_allowed:Boolean,

):Serializable