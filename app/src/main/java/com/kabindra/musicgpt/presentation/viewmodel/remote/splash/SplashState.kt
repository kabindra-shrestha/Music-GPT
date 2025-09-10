package com.kabindra.musicgpt.presentation.viewmodel.remote.splash

import com.kabindra.musicgpt.utils.constants.ResponseType

data class SplashState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val successType: ResponseType = ResponseType.None,
    val successMessage: String = "",
    val errorType: ResponseType = ResponseType.None,
    val errorStatusCode: Int = -1,
    val errorTitle: String = "",
    val errorMessage: String = "",
    val isCheckSplash: Boolean? = false
)
