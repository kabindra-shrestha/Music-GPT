package com.kabindra.musicgpt.domain.model

import com.kabindra.musicgpt.utils.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val response: HomeData?
) : BaseResponse()

@Serializable
data class HomeData(
    val music: List<Music>? = listOf()
)

@Serializable
data class Music(
    val id: Int? = 0,
    val title: String? = "",
    val description: String? = "",
    val image: Int? = 0,
    val actionType: String? = "",
)