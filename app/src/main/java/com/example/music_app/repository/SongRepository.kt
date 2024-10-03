package com.example.music_app.repository

import androidx.annotation.DrawableRes
import com.example.music_app.R

object SongRepository {
    val songs = listOf<Song>(
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            1,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 1",
            R.drawable.classic_category_image
        ),
        Song (
            2,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                2,
                "classic",
                R.drawable.classic_category_image
            ),
            2,
            "232",
            1110,
            "Bài hát 2",
            R.drawable.classic_category_image
        ),
        Song (
            3,
            User(
                1,
                "Lê Hoàng Chiến",
                1,
                "hoangchien@gmail.com",
                1000f,
                "avatar"

            ),
            Category(
                3,
                "pop",
                R.drawable.classic_category_image
            ),
            2,
            "2222",
            1000,
            "Bài hát 3",
            R.drawable.classic_category_image
        )
    )
}


data class Song(
    val id: Int,
    val author: User,
    val category: Category,
    val state: Int,
    val create_at:String,
    val view_count:Int,
    val name:String,
    @DrawableRes val image: Int

)