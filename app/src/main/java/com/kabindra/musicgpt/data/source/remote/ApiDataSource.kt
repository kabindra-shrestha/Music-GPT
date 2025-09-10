package com.kabindra.musicgpt.data.source.remote

import com.kabindra.musicgpt.data.model.MusicDTO

class ApiDataSource() {

    suspend fun getHome(): List<MusicDTO> {
        return getMockMusics()
    }

}

fun getMockMusics(): List<MusicDTO> {
    return listOf(
        MusicDTO(1, "Alice Johnson", "alice@example.com", "", ""),
        MusicDTO(2, "Alice Johnson", "alice@example.com", "", ""),
        MusicDTO(3, "Alice Johnson", "alice@example.com", "", ""),
        MusicDTO(4, "Alice Johnson", "alice@example.com", "", ""),
        MusicDTO(5, "Alice Johnson", "alice@example.com", "", ""),
        MusicDTO(6, "Alice Johnson", "alice@example.com", "", ""),
    )
}