package com.example.music_app.domain.model

import androidx.annotation.DrawableRes
import com.example.music_app.R

object CategoryRepository {
    val listCategory = listOf<Category>(
        Category(
            1,
            "acoustic",
            R.drawable.classic_category_image
        ),
        Category(
            2,
            "classic",
            R.drawable.classic_category_image
        ),
        Category(
            3,
            "pop",
            R.drawable.classic_category_image
        )
    )
}

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int
)