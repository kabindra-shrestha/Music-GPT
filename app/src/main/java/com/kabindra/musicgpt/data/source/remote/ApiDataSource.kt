package com.kabindra.musicgpt.data.source.remote

import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.data.model.MusicDTO

class ApiDataSource() {

    suspend fun getHome(): List<MusicDTO> {
        return getMockMusics()
    }

}

fun getMockMusics(): List<MusicDTO> {
    return listOf(
        MusicDTO(1, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(2, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(3, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(4, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(5, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(6, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(7, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(8, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(9, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
        MusicDTO(10, "Alice Johnson", "alice@example.com", R.drawable.splash_icon, ""),
    )
}