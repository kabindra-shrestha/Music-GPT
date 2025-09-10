package com.kabindra.musicgpt.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.presentation.ui.screen.navigation.Route
import com.kabindra.musicgpt.presentation.ui.theme.bottomNavigationSelected
import com.kabindra.musicgpt.presentation.ui.theme.bottomNavigationUnselected
import com.kabindra.musicgpt.presentation.ui.theme.transparent
import com.kabindra.musicgpt.utils.enums.MenuType

@Composable
fun Lifecycle.observeAsSate(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsSate.addObserver(observer)
        onDispose {
            this@observeAsSate.removeObserver(observer)
        }
    }
    return state
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    canNavigateBack: Boolean,
    onBackNavigate: () -> Unit
) {
    TopAppBar(
        title = {
            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                size = TextSize.Small,
                textAlign = TextAlign.Start
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                ButtonBack {
                    onBackNavigate()
                }
            }
        }
    )
}

@Composable
fun TopAppBarWithIconAndNameComponent(
    modifier: Modifier = Modifier.height(54.dp)
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ImageHandlerRes(
            modifier = modifier,
            image = R.drawable.splash_icon,
            contentDescription = "App Icon",
        )
        TextComponent(
            modifier = Modifier.fillMaxWidth(),
            text = LocalResources.current.getString(R.string.app_name),
            type = TextType.Title,
            size = TextSize.Large,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun TopAppBarWithBackComponent(
    modifier: Modifier = Modifier.height(54.dp),
    title: String = "",
    onBackNavigate: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ButtonBack {
            onBackNavigate()
        }
        TextComponent(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            size = TextSize.Large,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun BottomNavigationBarComponent(
    modifier: Modifier = Modifier,
    selectedRoute: String = Route.DashboardRoute::class.qualifiedName!!,
    onClick: (selectedSlug: String) -> Unit
) {
    Column {
        HorizontalDivider()
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            MenuType.entries
                .filter { it.isBottomNavigation }
                .forEachIndexed { index, label ->
                    NavigationBarItem(
                        icon = {
                            label.icon?.let {
                                ImageHandlerRes(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .padding(1.dp),
                                    image = it,
                                    contentDescription = label.title,
                                    tint = if (label.route != null && selectedRoute.contains(label.route::class.qualifiedName!!)) {
                                        bottomNavigationSelected
                                    } else {
                                        bottomNavigationUnselected
                                    }
                                )
                            }
                        },
                        label = {
                            /*TextComponent(
                                text = label.title,
                                textAlign = TextAlign.Center,
                                maxLines = 3,
                                size = TextSize.Small
                            )*/
                        },
                        selected = if (label.route != null) {
                            selectedRoute.contains(label.route::class.qualifiedName!!)
                        } else {
                            false
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = bottomNavigationSelected,
                            unselectedIconColor = bottomNavigationUnselected,
                            selectedTextColor = bottomNavigationSelected,
                            unselectedTextColor = bottomNavigationUnselected,
                            indicatorColor = transparent
                        ),
                        onClick = {
                            onClick(label.slug)
                        }
                    )
                }
        }
    }
}