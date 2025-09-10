package com.kabindra.musicgpt.data.model

import com.kabindra.musicgpt.domain.model.Home
import com.kabindra.musicgpt.domain.model.HomeData
import com.kabindra.musicgpt.domain.model.Music
import com.kabindra.musicgpt.utils.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class HomeDTO(
    val response: HomeDataDTO?
) : BaseResponse()

@Serializable
data class HomeDataDTO(
    val music: List<MusicDTO> = listOf()
)

@Serializable
data class MusicDTO(
    val id: Int? = 0,
    val title: String? = "",
    val description: String = "",
    val image: String? = "",
    val actionType: String? = "",
)

// Mapper function
fun HomeDTO.toDomain(): Home {
    return Home(
        response = response?.toDomain(),
    ).apply {
        status = this@toDomain.status
        statusCode = this@toDomain.statusCode
        message = this@toDomain.message
    }
}

fun HomeDataDTO.toDomain(): HomeData {
    return HomeData(
        music = music.map { it.toDomain() },
    )
}

fun MusicDTO.toDomain(): Music {
    return Music(
        id = id,
        title = title,
        description = description,
        image = image,
        actionType = actionType,
    )
}