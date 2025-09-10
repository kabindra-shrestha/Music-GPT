package com.kabindra.musicgpt.utils.base

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    var title: String? = "",
    val errors: Map<String, List<String>>? = null
) : BaseResponse()