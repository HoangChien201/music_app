package com.example.music_app.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.music_app.core.Converters


object ArtistRepository{
    val artists= listOf<Artist>(
        Artist(
            "596751",
            "Sarah C",
         "",
    "2024-11-15",
    "https:\\/\\/usercontent.jamendo.com?type=artist&id=376782&width=300",
            listOf(
                Track(

                        album_id= "593403",
                        album_name="My Heart Is Your Home",
                        id="2232438".toInt(),
                        name="My Heart Is Your Home",
                        duration="202",
                        releasedate="2025-01-27",
                        license_ccurl="http://creativecommons.org/licenses/by/3.0/",
                        album_image="https://usercontent.jamendo.com?type=album&id=593403&width=300&trackid=2232438",
                        image="https://usercontent.jamendo.com?type=album&id=593403&width=300&trackid=2232438",
                        audio="https://prod-1.storage.jamendo.com/?trackid=2232438&format=mp31&from=%2BfvpLrHfEqK%2BnjZ36kHMPw%3D%3D%7CitvINK1U6gtdvr5ActE9Ow%3D%3D",
                        audiodownload="https://prod-1.storage.jamendo.com/download/track/2232438/mp31/",
                        audiodownload_allowed= true,
                    position = null

                )
            )),

            Artist(
                "595588",
                "GK Menage",
                "https://www.gkmenage.org",
                "2024-11-15",
                "https://usercontent.jamendo.com?type=artist&id=593864&width=300",
                listOf(
                    Track(
                        album_id= "589333",
                        album_name= "Another Beautiful Sunset",
                        id="2222158".toInt(),
                        name= "Another Beautiful Sunset",
                        duration="186",
                        releasedate="2024-12-09",
                        license_ccurl="http://creativecommons.org/licenses/by-nc-nd/3.0/",
                        album_image= "https://usercontent.jamendo.com?type=album&id=589333&width=300&trackid=2222158",
                        image= "https://usercontent.jamendo.com?type=album&id=589333&width=300&trackid=2222158",
                        audio= "https://prod-1.storage.jamendo.com/?trackid=2222158&format=mp31&from=R7g2hikL8vX%2Bn0DLGC%2FtAA%3D%3D%7CR3iP6TVC2445Be4znOWVBg%3D%3D",
                        audiodownload= "https://prod-1.storage.jamendo.com/download/track/2222158/mp31/",
                        audiodownload_allowed= true,

                    ),
                    Track(
                        album_id= "589322",
                        album_name= "TE AMO A TI",
                        id= "2222139".toInt(),
                        name= "TE AMO A TI",
                        duration= "209",
                        releasedate= "2024-12-09",
                        license_ccurl= "http://creativecommons.org/licenses/by-nc-nd/3.0/",
                        album_image= "https://usercontent.jamendo.com?type=album&id=589322&width=300&trackid=2222139",
                        image= "https://usercontent.jamendo.com?type=album&id=589322&width=300&trackid=2222139",
                        audio= "https://prod-1.storage.jamendo.com/?trackid=2222139&format=mp31&from=cntLX8llRdvcnlbjpsvjsA%3D%3D%7CLy%2FwUHC9k%2F8Ko5CJXxPsXg%3D%3D",
                        audiodownload= "https://prod-1.storage.jamendo.com/download/track/2222139/mp31/",
                        audiodownload_allowed= true,

                    ),
                    Track(
                        album_id= "582367",
                        album_name= "DANCING ON THE BEACH",
                        id= "2208429".toInt(),
                        name= "DANCING ON THE BEACH",
                        duration= "189",
                        releasedate= "2024-10-09",
                        license_ccurl= "http://creativecommons.org/licenses/by-nc-nd/3.0/",
                        album_image= "https://usercontent.jamendo.com?type=album&id=582367&width=300&trackid=2208429",
                        image= "https://usercontent.jamendo.com?type=album&id=582367&width=300&trackid=2208429",
                        audio= "https://prod-1.storage.jamendo.com/?trackid=2208429&format=mp31&from=nQkTvzgABs3zcGUYCSwjuA%3D%3D%7CCUiQaWGgLhXV%2F6WzQTCzfA%3D%3D",
                        audiodownload="https://prod-1.storage.jamendo.com/download/track/2208429/mp31/",
                        audiodownload_allowed= true,

                    )
                )
        )
    )
}

@Entity
@TypeConverters(Converters::class)
data class Artist(
    @PrimaryKey
    val id:String,
    val name:String,
    val website:String,
    val joindate:String,
    val image:String,
    val tracks:List<Track>
)