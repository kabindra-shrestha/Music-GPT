package com.kabindra.musicgpt.presentation.viewmodel.remote.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.domain.usecase.remote.HomeUseCase
import com.kabindra.musicgpt.utils.constants.ResponseType
import com.kabindra.musicgpt.utils.enums.CreationType
import com.kabindra.musicgpt.utils.ktor.Result
import com.kabindra.musicgpt.utils.mapToCheckpoint
import com.kabindra.musicgpt.utils.mapToCreationImage
import com.kabindra.musicgpt.utils.mapToCreationType
import kotlinx.coroutines.delay
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

            is HomeEvent.QueueSong -> {
                queueSong(event.size)
            }

            is HomeEvent.GenerateSong -> {
                generateSong(event.size)
            }

            is HomeEvent.UpdateProgressSong -> {
                updateProgressSong(event.id, event.checkPoint)
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

    fun queueSong(size: Int) {
        viewModelScope.launch {
            homeUseCase.executeQueueSong(size)
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
                            val oldList = _homeState.value.homeData?.music.orEmpty()
                            val newList = oldList.toMutableList().apply {
                                add(0, result.data)
                            }

                            _homeState.value =
                                _homeState.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    successType = ResponseType.None,
                                    successMessage = "",
                                    homeData = _homeState.value.homeData?.copy(
                                        music = newList
                                    )
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

    fun generateSong(size: Int) {
        viewModelScope.launch {
            homeUseCase.executeGenerateSong(size)
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
                            val oldList = _homeState.value.homeData?.music.orEmpty()
                            val newList = oldList.toMutableList().apply {
                                add(0, result.data)
                            }

                            _homeState.value =
                                _homeState.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    successType = ResponseType.None,
                                    successMessage = "",
                                    homeData = _homeState.value.homeData?.copy(
                                        music = newList
                                    )
                                )

                            result.data.id?.let { startProgress(it) }
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

    fun updateProgressSong(id: Int, checkPoint: Int) {
        viewModelScope.launch {
            val creationType = if (checkPoint == -1) {
                CreationType.Generated.slug
            } else {
                mapToCreationType(checkPoint)
            }

            val image = if (checkPoint == -1) {
                R.drawable.propertyfinish
            } else {
                mapToCreationImage(checkPoint)
            }

            val oldList = _homeState.value.homeData?.music.orEmpty()
            val newList = oldList.map { music ->
                if (music.id == id) {
                    music.copy(image = image, creationType = creationType) //
                } else music
            }

            _homeState.value = _homeState.value.copy(
                homeData = _homeState.value.homeData?.copy(music = newList)
            )
        }
    }

    private fun startProgress(id: Int) {
        viewModelScope.launch {
            var progress = 0
            var lastCheckpoint = -1

            while (progress <= 100) {
                val checkpoint = mapToCheckpoint(progress)

                // only emit when we hit a new checkpoint
                if (checkpoint != lastCheckpoint) {
                    onEvent(HomeEvent.UpdateProgressSong(id, checkpoint))
                    lastCheckpoint = checkpoint
                }

                delay(100) // adjust speed
                progress += 1
            }

            // After loop → mark as completed
            onEvent(HomeEvent.UpdateProgressSong(id, -1)) // special completed state
        }
    }


    fun resetStates() {
        _homeState.value = HomeState()
    }
}