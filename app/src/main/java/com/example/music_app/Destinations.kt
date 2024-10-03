package com.example.music_app

interface Destinations {
    val route: String
}

object HomeDestination : Destinations {
    override val route = "Home"
}

object SongDestination : Destinations {
    override val route = "Song"
    const val songId="songId"
}
object CategoryDestination : Destinations {
    override val route = "Category"
    const val categoryId="categoryId"

}


