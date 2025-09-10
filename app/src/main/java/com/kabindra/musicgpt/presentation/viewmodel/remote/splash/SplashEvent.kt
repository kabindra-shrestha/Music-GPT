package com.kabindra.musicgpt.presentation.viewmodel.remote.splash

sealed class SplashEvent {
    data object CheckSplash : SplashEvent()
}