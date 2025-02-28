package com.example.music_app.repository

import androidx.annotation.DrawableRes
import com.example.music_app.R
import com.example.music_app.domain.model.Category

object SongRepository {
    val songs = listOf<Song>(
        Song (
            1,
            "Sơn Tùng MTP",
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            "",
            "Cơn Mưa Ngang Qua",
            R.raw.conmuangangqua_sontungmtp,
            R.drawable.sontungmtp_1
        ),
        Song (
            2,
            "Hiếu Thứ Hai",
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            "",
            "Exit Sign",
            R.raw.exitsign_hieuthuhai,
            R.drawable.ht2
        ),
        Song (
            3,
            "Ngô Lan Hương",
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            "",
            "Đi Giữa Trời Rực Rỡ",
            R.raw.digiuatroirucro,
            R.drawable.dgtrr_img
        ),
        Song (
            4,
            "Tuấn Hưng",
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            "",
            "Vẫn Nhớ",
            R.raw.vannho,
            R.drawable.tuanhung
        ),
        Song (
            5,
            "Kim Tiểu Phương",
            Category(
                1,
                "acoustic",
                R.drawable.classic_category_image
            ),
            "",
            "Hello Việt Nam",
            R.raw.hellovn_kimtieuphuong,
            R.drawable.hellovn_img
        ),

    )
}


data class Song(
    val id: Int,
    val author: String,
    val category: Category,
    val create_at:String,
    val name:String,
    val resource:Int,
    @DrawableRes val image: Int

)