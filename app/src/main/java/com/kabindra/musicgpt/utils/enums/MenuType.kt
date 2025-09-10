package com.kabindra.musicgpt.utils.enums

import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.presentation.ui.screen.navigation.Route

enum class MenuType(
    val title: String,
    val icon: Int?,
    val slug: String,
    val route: Route?,
    val isBottomNavigation: Boolean = false
) {
    Home(
        "Home",
        R.drawable.generate_ai_icon,
        "home",
        Route.HomeRoute,
        isBottomNavigation = true
    ),
    Search(
        "Search",
        R.drawable.discover_icon,
        "search",
        Route.SearchRoute,
        isBottomNavigation = true
    ),
    Recommended(
        "Recommended",
        R.drawable.user_icon,
        "recommended",
        Route.RecommendedRoute,
        isBottomNavigation = true
    ),
    Profile(
        "Profile",
        R.drawable.reel_icon,
        "profile",
        Route.ProfileRoute,
        isBottomNavigation = true
    ),
}

inline fun <reified T : Enum<T>> getMenuType(slug: String): MenuType {
    return enumValues<T>().find { (it as MenuType).slug == slug } as MenuType
}