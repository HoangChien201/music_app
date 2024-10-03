interface Destination {
    val route:String
}

object Home:Destination{
    override val route="Home"
}

object SongPlay:Destination{
    override val route="SongPlay"
    const val songId="songId"
}

object CategoryDetail:Destination{
    override val route="CategoryDetail"
}

