package com.kabindra.musicgpt.presentation.ui.screen.navigation

import kotlinx.serialization.Serializable

enum class Screens(val title: String) {
    Splash(title = "Splash"),
    Dashboard(title = "Dashboard"),
    Home(title = "Home"),
    Search(title = "Search"),
    Recommended(title = "Recommended"),
    Profile(title = "Profile"),
}

sealed class Route {
    @Serializable
    data object SplashRoute : Route()

    @Serializable
    data object DashboardRoute : Route()

    @Serializable
    data object HomeMainRoute : Route()

    @Serializable
    data object HomeRoute : Route()

    @Serializable
    data object SearchRoute : Route()

    @Serializable
    data object RecommendedRoute : Route()

    @Serializable
    data object ProfileRoute : Route()
}