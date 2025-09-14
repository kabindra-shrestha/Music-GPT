package com.kabindra.musicgpt.presentation.viewmodel.remote.home

sealed class HomeEvent {
    data object CheckHome : HomeEvent()
    data class QueueSong(val size: Int) : HomeEvent()
    data class GenerateSong(val size: Int) : HomeEvent()
    data class UpdateProgressSong(val id: Int, val checkPoint: Int) : HomeEvent()
}