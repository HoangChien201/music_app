package com.example.music_app.destination

interface Destinations {
    val route: String
}

object HomeDestination : Destinations {
    override val route = "Home"
}

object TrackDestination : Destinations {
    override val route = "Song"
    const val trackId="trackId"
    const val category="category"
    const val topicId="topicId"


}
object TopicDestination : Destinations {
    override val route = "Topic"
    const val topicId="topicId"
    const val category="category"

}


