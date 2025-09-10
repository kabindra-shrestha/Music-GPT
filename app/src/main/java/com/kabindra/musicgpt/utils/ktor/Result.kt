package com.kabindra.musicgpt.utils.ktor

import com.kabindra.musicgpt.utils.base.ErrorResponse

sealed class Result<out T> {
    data object Initial : Result<Nothing>()
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: ErrorResponse) : Result<Nothing>()
}
