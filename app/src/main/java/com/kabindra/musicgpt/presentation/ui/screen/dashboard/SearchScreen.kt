package com.kabindra.musicgpt.presentation.ui.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kabindra.musicgpt.presentation.ui.component.TextComponent
import com.kabindra.musicgpt.presentation.ui.component.TextType
import com.kabindra.musicgpt.presentation.ui.component.TopAppBarWithIconAndNameComponent

@Composable
fun SearchScreen(
    innerPadding: PaddingValues,
    onBackNavigate: () -> Unit
) {

    // Use DisposableEffect to reset states when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            // Reset the relevant states
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBarWithIconAndNameComponent(
                modifier = Modifier
                    .height(54.dp),
                name = "Discover"
            )
        }

        TextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            type = TextType.Headline,
            text = "Coming Soon",
            textAlign = TextAlign.Center
        )
    }

}