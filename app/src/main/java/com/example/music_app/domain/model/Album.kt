package com.example.music_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


object AlbumsRepository {
    val albums = listOf<Album>(
        Album(
            "104336".toInt(),
        "Season One",
    "2011-12-29",
    "376782",
    "WE ARE FM",
    "https://usercontent.jamendo.com?type=album&id=104336&width=300",
    "https://storage.jamendo.com/download/a104336/mp32/",
    true,
            listOf(
                Track(
                    id="887202".toInt(),
                position="10",
                name= "Press Record",
                duration= "192",
                license_ccurl= "http://creativecommons.org/licenses/by-nc-sa/3.0/",
                audio="https://prod-1.storage.jamendo.com/?trackid=887202&format=mp31&from=TtMTDn9g99BOEOfFeKO46w%3D%3D%7CcAsRGU2VX0NFRBwztwamQg%3D%3D",
                audiodownload= "https://prod-1.storage.jamendo.com/download/track/887202/mp32/",
                audiodownload_allowed= true,


                )
            )

        )
    )
}

@Entity
data class Album(
    @PrimaryKey
    override val id: Int,
    override val name: String,
    val release_date: String,
    val artists_id: String,
    val artists_name: String,
    override val image: String,
    val zip:String,
    val zip_allowed:Boolean,
    override val tracks:List<Track>
):Topic(id, name, tracks,image)