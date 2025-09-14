package com.kabindra.musicgpt.presentation.viewmodel.remote.home

import com.kabindra.musicgpt.domain.model.HomeData
import com.kabindra.musicgpt.utils.constants.ResponseType

data class HomeState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val successType: ResponseType = ResponseType.None,
    val successMessage: String = "",
    val errorType: ResponseType = ResponseType.None,
    val errorStatusCode: Int = -1,
    val errorTitle: String = "",
    val errorMessage: String = "",
    val homeData: HomeData? = null
)
