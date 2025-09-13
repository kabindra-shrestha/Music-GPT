package com.kabindra.musicgpt.presentation.ui.screen.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.domain.model.Music
import com.kabindra.musicgpt.presentation.ui.component.BaseLazy
import com.kabindra.musicgpt.presentation.ui.component.ButtonIconAndTextRes
import com.kabindra.musicgpt.presentation.ui.component.LazyListType
import com.kabindra.musicgpt.presentation.ui.component.LazyScrollDirection
import com.kabindra.musicgpt.presentation.ui.component.LoadingIndicator
import com.kabindra.musicgpt.presentation.ui.component.ShowEmpty
import com.kabindra.musicgpt.presentation.ui.component.TopAppBarWithIconAndNameComponent
import com.kabindra.musicgpt.presentation.ui.items.ItemHomeMusic
import com.kabindra.musicgpt.presentation.ui.items.ItemHomePlayControl
import com.kabindra.musicgpt.presentation.ui.theme.buttonBackgroundColor
import com.kabindra.musicgpt.presentation.viewmodel.remote.home.HomeEvent
import com.kabindra.musicgpt.presentation.viewmodel.remote.home.HomeViewModel
import com.kabindra.musicgpt.utils.Connectivity
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    innerPadding: PaddingValues
) {

    val connectivity = remember { Connectivity() }
    val isConnected by connectivity.isConnectedState.collectAsState()
    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()
    var musicSelected by remember { mutableStateOf<Music?>(null) }

    // Use DisposableEffect to reset states when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            // Reset the relevant states
            homeViewModel.resetStates()
        }
    }

    // Check internet connection
    /*if (!isConnected) {
        GlobalErrorDialog(
            isVisible = true,
            statusCode = -1,
            title = ERROR_TITLE_NO_NETWORK_CONNECTIVITY,
            message = ERROR_NO_NETWORK_CONNECTIVITY,
            onDismiss = {
            },
        )
        return
    }*/

    LaunchedEffect(Unit) {
        homeViewModel.onEvent(HomeEvent.CheckHome)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        if (homeState.isLoading) {
            LoadingIndicator(
                modifier = Modifier.align(Alignment.Center),
                isCircular = true
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBarWithIconAndNameComponent(
                modifier = Modifier
                    .height(54.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                if (!homeState.homeData?.music.isNullOrEmpty()) {
                    BaseLazy(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(0.dp),
                        arrangement = Arrangement.spacedBy(0.dp),
                        items = homeState.homeData?.music!!,
                        listType = LazyListType.LIST,
                        scrollDirection = LazyScrollDirection.VERTICAL,
                        itemContent = { index, item ->
                            ItemHomeMusic(item, musicSelected) {
                                musicSelected = item
                            }
                        },
                        onLoadMore = { },
                        onScrollStateChanged = { },
                    )
                }

                if (homeState.homeData?.music?.isEmpty() ?: false) {
                    ShowEmpty()
                }
            }

            ButtonIconAndTextRes(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                iconRes = R.drawable.stars_01_1,
                text = "Create",
                buttonColors = ButtonDefaults.buttonColors(buttonBackgroundColor())
            ) {
                musicSelected = null
            }

            Spacer(modifier = Modifier.height(8.dp))

            AnimatedVisibility(
                visible = musicSelected != null,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }
                ) + fadeOut()
            ) {
                musicSelected?.let { music ->
                    Column {
                        ItemHomePlayControl(music)

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

}