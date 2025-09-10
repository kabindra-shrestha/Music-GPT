package com.kabindra.musicgpt.presentation.ui.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kabindra.musicgpt.presentation.ui.component.AppIcon
import com.kabindra.musicgpt.presentation.ui.component.TextComponent
import com.kabindra.musicgpt.utils.Connectivity
import com.kabindra.musicgpt.utils.constants.ErrorType.Companion.ERROR_NO_NETWORK_CONNECTIVITY
import com.kabindra.musicgpt.utils.constants.ErrorType.Companion.ERROR_TITLE_NO_NETWORK_CONNECTIVITY
import com.kabindra.musicgpt.utils.error.GlobalErrorDialog

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    onBackNavigate: () -> Unit
) {

    val connectivity = remember { Connectivity() }
    val isConnected by connectivity.isConnectedState.collectAsState()

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

    Box(modifier = Modifier.fillMaxSize()) {
        AppIcon(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .align(Alignment.Center)
        )

        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = (-75).dp),
            text = "Profile Screen",
            textAlign = TextAlign.Center
        )
    }

}