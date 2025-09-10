package com.kabindra.musicgpt.data.model

import com.kabindra.musicgpt.domain.model.SplashCheck
import com.kabindra.musicgpt.utils.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class SplashCheckDTO(
    val response: Boolean?
) : BaseResponse()

// Mapper function
fun SplashCheckDTO.toDomain(): SplashCheck {
    return SplashCheck(
        response = response
    ).apply {
        status = this@toDomain.status
        statusCode = this@toDomain.statusCode
        message = this@toDomain.message
    }
}