package com.kabindra.musicgpt.presentation.viewmodel.remote.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabindra.musicgpt.domain.usecase.remote.HomeUseCase
import com.kabindra.musicgpt.utils.constants.ResponseType
import com.kabindra.musicgpt.utils.ktor.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())

    val homeState = _homeState
        .onStart { }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomeState()
        )

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CheckHome -> {
                checkHome()
            }
        }
    }

    fun checkHome() {
        viewModelScope.launch {
            homeUseCase.executeHomeData()
                .collect { result ->
                    when (result) {
                        is Result.Initial -> Unit
                        is Result.Loading -> {
                            _homeState.value =
                                _homeState.value.copy(
                                    isLoading = true
                                )
                        }

                        is Result.Success -> {
                            _homeState.value =
                                _homeState.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    successType = ResponseType.None,
                                    successMessage = "",
                                    homeData = result.data.response
                                )
                        }

                        is Result.Error -> {
                            _homeState.value =
                                _homeState.value.copy(
                                    isLoading = false,
                                    isError = true,
                                    errorType = ResponseType.None,
                                    errorStatusCode = result.error.statusCode,
                                    errorTitle = "",
                                    errorMessage = result.error.message
                                )
                        }
                    }
                }
        }
    }

    fun resetStates() {
        _homeState.value = HomeState()
    }
}