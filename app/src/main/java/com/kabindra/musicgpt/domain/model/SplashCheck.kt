package com.kabindra.musicgpt.domain.model

import com.kabindra.musicgpt.utils.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class SplashCheck(
    val response: Boolean?
) : BaseResponse()