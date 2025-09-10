package com.kabindra.musicgpt.utils.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.kabindra.musicgpt.presentation.ui.screen.navigation.Route

enum class MenuType(
    val title: String,
    val icon: ImageVector?,
    val slug: String,
    val route: Route?,
    val isBottomNavigation: Boolean = false
) {
    Home(
        "Home",
        Icons.Default.Dashboard,
        "home",
        Route.HomeRoute,
        isBottomNavigation = true
    ),
    Search(
        "Search",
        Icons.Default.Search,
        "search",
        Route.SearchRoute,
        isBottomNavigation = true
    ),
    Recommended(
        "Recommended",
        Icons.Default.Recommend,
        "recommended",
        Route.RecommendedRoute,
        isBottomNavigation = true
    ),
    Profile(
        "Profile",
        Icons.Default.Person,
        "profile",
        Route.ProfileRoute,
        isBottomNavigation = true
    ),
}

inline fun <reified T : Enum<T>> getMenuType(slug: String): MenuType {
    return enumValues<T>().find { (it as MenuType).slug == slug } as MenuType
}