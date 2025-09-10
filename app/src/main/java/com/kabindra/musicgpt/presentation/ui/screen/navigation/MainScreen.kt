package com.kabindra.musicgpt.presentation.ui.screen.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kabindra.musicgpt.presentation.ui.screen.dashboard.DashboardScreen
import com.kabindra.musicgpt.presentation.ui.screen.splash.SplashScreen
import org.koin.compose.koinInject

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val snackBarHostState: SnackbarHostState = koinInject()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.SplashRoute,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable<Route.SplashRoute> {
                SplashScreen(
                    innerPadding = innerPadding,
                    onNavigateDashboard = {
                        navController.navigate(Route.DashboardRoute) {
                            popUpTo(Route.SplashRoute) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<Route.DashboardRoute> {
                DashboardScreen(
                    innerPadding = innerPadding
                )
            }
        }
    }
}

fun AppBackHandler(navController: NavHostController) {
    if (navController.previousBackStackEntry != null) {
        navController.popBackStack()
    } else {
        navController.popBackStack()
    }
}