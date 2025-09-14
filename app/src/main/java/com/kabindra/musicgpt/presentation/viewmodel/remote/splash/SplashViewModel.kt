package com.kabindra.musicgpt.presentation.viewmodel.remote.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabindra.musicgpt.domain.usecase.remote.SplashUseCase
import com.kabindra.musicgpt.utils.constants.ResponseType
import com.kabindra.musicgpt.utils.ktor.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SplashViewModel(
    private val splashUseCase: SplashUseCase
) : ViewModel() {
    private val _splashState = MutableStateFlow(SplashState())

    val splashState = _splashState
        .onStart { }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SplashState()
        )

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.CheckSplash -> {
                checkSplash()
            }
        }
    }

    fun checkSplash() {
        viewModelScope.launch {
            splashUseCase.executeCheckSplash()
                .collect { result ->
                    when (result) {
                        is Result.Initial -> Unit
                        is Result.Loading -> {
                            _splashState.value =
                                _splashState.value.copy(
                                    isLoading = true
                                )
                        }

                        is Result.Success -> {
                            _splashState.value =
                                _splashState.value.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    successType = ResponseType.None,
                                    successMessage = "",
                                    isCheckSplash = result.data.response
                                )
                        }

                        is Result.Error -> {
                            _splashState.value =
                                _splashState.value.copy(
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
        _splashState.value = SplashState()
    }
}