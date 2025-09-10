package com.kabindra.musicgpt.presentation.ui.screen.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.kabindra.musicgpt.presentation.ui.component.BottomNavigationBarComponent
import com.kabindra.musicgpt.presentation.ui.screen.navigation.AppBackHandler
import com.kabindra.musicgpt.presentation.ui.screen.navigation.Route
import com.kabindra.musicgpt.utils.Connectivity
import com.kabindra.musicgpt.utils.constants.ErrorType.Companion.ERROR_NO_NETWORK_CONNECTIVITY
import com.kabindra.musicgpt.utils.constants.ErrorType.Companion.ERROR_TITLE_NO_NETWORK_CONNECTIVITY
import com.kabindra.musicgpt.utils.enums.MenuType
import com.kabindra.musicgpt.utils.enums.getMenuType
import com.kabindra.musicgpt.utils.error.GlobalErrorDialog

@Composable
fun DashboardScreen(
    innerPadding: PaddingValues
) {
    val navController = rememberNavController()

    val connectivity = remember { Connectivity() }
    val isConnected by connectivity.isConnectedState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = backStackEntry?.destination?.route?.let { mutableStateOf(it) }
        ?: mutableStateOf(Route.HomeMainRoute::class.qualifiedName)

    // Use DisposableEffect to reset states when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            // Reset the relevant states
        }
    }

    if (!isConnected) {
        GlobalErrorDialog(
            isVisible = true,
            statusCode = -1,
            title = ERROR_TITLE_NO_NETWORK_CONNECTIVITY,
            message = ERROR_NO_NETWORK_CONNECTIVITY,
            onDismiss = {
            },
        )
        return
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBarComponent(
                selectedRoute = currentRoute.value!!,
                onClick = { selectedSlug ->
                    if (getMenuType<MenuType>(selectedSlug).route != null) {
                        val navigationRoute: Route =
                            getMenuType<MenuType>(selectedSlug).route!!

                        navController.navigate(navigationRoute) {
                            popUpTo(navController.graph.findStartDestination().route!!) {
                                saveState = true
                            }

                            launchSingleTop = true

                            restoreState = true
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.HomeMainRoute
        ) {
            navigation<Route.HomeMainRoute>(startDestination = Route.HomeRoute) {
                composable<Route.HomeRoute> {
                    HomeScreen(
                        innerPadding = innerPadding
                    )
                }
                composable<Route.SearchRoute> {
                    SearchScreen(
                        innerPadding = innerPadding,
                        onBackNavigate = { AppBackHandler(navController) }
                    )
                }
                composable<Route.RecommendedRoute> {
                    RecommendedScreen(
                        innerPadding = innerPadding,
                        onBackNavigate = { AppBackHandler(navController) }
                    )
                }
                composable<Route.ProfileRoute> {
                    ProfileScreen(
                        innerPadding = innerPadding,
                        onBackNavigate = { AppBackHandler(navController) },
                    )
                }
            }
        }
    }

}