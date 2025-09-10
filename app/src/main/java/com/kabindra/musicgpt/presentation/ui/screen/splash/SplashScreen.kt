package com.kabindra.musicgpt.presentation.ui.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kabindra.musicgpt.presentation.ui.component.AppIcon
import com.kabindra.musicgpt.presentation.ui.component.LoadingIndicator
import com.kabindra.musicgpt.presentation.ui.component.TextComponent
import com.kabindra.musicgpt.presentation.viewmodel.remote.splash.SplashEvent
import com.kabindra.musicgpt.presentation.viewmodel.remote.splash.SplashViewModel
import com.kabindra.musicgpt.utils.Connectivity
import com.kabindra.musicgpt.utils.constants.ErrorType.Companion.ERROR_NO_NETWORK_CONNECTIVITY
import com.kabindra.musicgpt.utils.constants.ErrorType.Companion.ERROR_TITLE_NO_NETWORK_CONNECTIVITY
import com.kabindra.musicgpt.utils.error.GlobalErrorDialog
import com.kabindra.musicgpt.utils.getPlatform
import com.kabindra.musicgpt.utils.success.GlobalSuccessDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = koinViewModel(),
    innerPadding: PaddingValues,
    onNavigateDashboard: () -> Unit
) {

    val connectivity = remember { Connectivity() }
    val isConnected by connectivity.isConnectedState.collectAsState()
    val splashState by splashViewModel.splashState.collectAsStateWithLifecycle()

    // Use DisposableEffect to reset states when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            // Reset the relevant states
            splashViewModel.resetStates()
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

    LaunchedEffect(Unit) {
        splashViewModel.onEvent(SplashEvent.CheckSplash)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AppIcon(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .align(Alignment.Center)
        )

        if (splashState.isLoading) {
            LoadingIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.Center)
                    .offset(y = 150.dp)
            )
        }

        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = (-75).dp),
            text = "Version: ${getPlatform().appVersion}",
            textAlign = TextAlign.Center
        )
    }

    LaunchedEffect(splashState.isCheckSplash) {
        if (splashState.isCheckSplash == true) {
            onNavigateDashboard()
        }
    }

    if (splashState.isSuccess) {
        GlobalSuccessDialog(
            isVisible = true,
            isAction = true,
            message = splashState.successMessage,
            onDismiss = { })
    }

    if (splashState.isError) {
        GlobalErrorDialog(
            isVisible = true,
            isAction = true,
            statusCode = splashState.errorStatusCode,
            title = splashState.errorTitle,
            message = splashState.errorMessage,
            onDismiss = {
            })
    }
}