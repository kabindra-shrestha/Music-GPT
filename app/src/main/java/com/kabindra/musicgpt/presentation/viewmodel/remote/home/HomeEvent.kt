package com.kabindra.musicgpt.presentation.viewmodel.remote.home

sealed class HomeEvent {
    data object CheckHome : HomeEvent()
}