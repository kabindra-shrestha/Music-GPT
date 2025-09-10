package com.kabindra.musicgpt.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.presentation.ui.theme.transparent

@Composable
fun ImageHandlerURL(
    modifier: Modifier = Modifier
        .width(250.dp)
        .height(250.dp),
    image: String = "",
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit,
    placeholder: ImageVector = Icons.Default.Image,
    tint: Color? = null,
    circular: Boolean = false,
    backgroundColor: Color = transparent,
    isClickable: Boolean = false,
    onClick: () -> Unit = {}
) {
    var loaded by remember { mutableStateOf(false) }

    AsyncImage(
        modifier = modifier
            .then(
                if (circular)
                    modifier
                        .clip(CircleShape)
                        .background(backgroundColor)
                else
                    modifier
            )
            .then(
                if (isClickable)
                    modifier.clickable { onClick() }
                else
                    modifier
            ),
        model = image,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = rememberVectorPainter(image = placeholder),
        error = rememberVectorPainter(image = placeholder),
        fallback = rememberVectorPainter(image = placeholder),
        onSuccess = {
            loaded = true
        },
        colorFilter = if (!loaded) ColorFilter.tint(tint ?: LocalContentColor.current) else null,
    )
}

@Composable
fun ImageHandlerRes(
    modifier: Modifier = Modifier
        .width(250.dp)
        .height(250.dp),
    image: Int = R.drawable.splash_icon,
    contentDescription: String = "",
    tint: Color? = null,
    circular: Boolean = false,
    backgroundColor: Color = transparent
) {
    Image(
        modifier = modifier
            .then(
                if (circular)
                    modifier
                        .clip(CircleShape)
                        .background(backgroundColor)
                else
                    modifier
            ),
        painter = painterResource(image),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        colorFilter = tint?.let { ColorFilter.tint(tint) }
    )
}

@Composable
fun ImageHandlerVector(
    modifier: Modifier = Modifier
        .width(250.dp)
        .height(250.dp),
    image: ImageVector?,
    contentDescription: String = "",
    tint: Color? = null,
    circular: Boolean = false,
    backgroundColor: Color = transparent,
    isClickable: Boolean = false,
    onClick: () -> Unit = {}
) {
    Icon(
        modifier = modifier
            .then(
                if (circular)
                    modifier
                        .clip(CircleShape)
                        .background(backgroundColor)
                else
                    modifier
            )
            .then(
                if (isClickable)
                    modifier.clickable { onClick() }
                else
                    modifier
            ),
        imageVector = image ?: Icons.Default.Image,
        contentDescription = contentDescription,
        tint = tint ?: LocalContentColor.current
    )
}

@Composable
fun ImageHandlerLottie(
    modifier: Modifier = Modifier
        .width(250.dp)
        .height(250.dp),
    image: LottieComposition?,
    contentDescription: String = "",
    circular: Boolean = false,
    backgroundColor: Color = transparent
) {
    LottieAnimation(
        modifier = modifier
            .then(
                if (circular)
                    modifier
                        .clip(CircleShape)
                        .background(backgroundColor)
                else
                    modifier
            ),
        composition = image,
        contentScale = ContentScale.Fit
    )
}