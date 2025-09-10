package com.kabindra.musicgpt.utils.constants

sealed class ResponseType {
    data object None : ResponseType()
    data object LoginCheckUser : ResponseType()
    data object HomeOTP : ResponseType()
    data object Logout : ResponseType()
    data object Refresh : ResponseType()

}