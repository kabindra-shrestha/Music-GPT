package com.kabindra.musicgpt.presentation.viewmodel.remote.home

sealed class HomeEvent {
    data object CheckHome : HomeEvent()
    data object QueueSong : HomeEvent()
    data object GenerateSong : HomeEvent()
}