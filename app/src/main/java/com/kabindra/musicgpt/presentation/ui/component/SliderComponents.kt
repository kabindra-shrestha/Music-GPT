package com.kabindra.musicgpt.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    sliderValue: Float,
    sliderRange: ClosedFloatingPointRange<Float>,
    onValueChange: (sliderValue: Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }

    Slider(
        modifier = modifier,
        value = sliderValue,
        onValueChange = { value ->
            onValueChange(value)
        },
        onValueChangeFinished = {
            onValueChangeFinished()
        },
        valueRange = sliderRange,
        enabled = enabled,
        steps = 0,
        colors = SliderDefaults.colors().copy(
            inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            thumbColor = Color.Transparent
        ),
        track = { sliderState ->
            SliderDefaults.Track(
                modifier = Modifier.height(2.dp),
                sliderState = sliderState
            )
        },
        thumb = {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(24.dp)
                    )
            )
        },
        interactionSource = interactionSource
    )
}