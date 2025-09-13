package com.kabindra.musicgpt.presentation.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.domain.model.Music
import com.kabindra.musicgpt.presentation.ui.component.CardBorderInside
import com.kabindra.musicgpt.presentation.ui.component.ImageHandlerRes
import com.kabindra.musicgpt.presentation.ui.component.SliderComponent
import com.kabindra.musicgpt.presentation.ui.component.TextComponent
import com.kabindra.musicgpt.presentation.ui.component.TextSize
import com.kabindra.musicgpt.presentation.ui.component.TextType
import com.kabindra.musicgpt.presentation.ui.theme.labelColor
import com.kabindra.musicgpt.presentation.ui.theme.overlay
import com.kabindra.musicgpt.presentation.ui.theme.playerBackgroundColor
import com.kabindra.musicgpt.presentation.ui.theme.titleColor
import com.kabindra.musicgpt.utils.enums.ActionType
import com.kabindra.musicgpt.utils.enums.getActionType

@Composable
fun ItemHomeMusic(
    content: Music,
    musicSelected: Music? = null,
    onClick: (Music) -> Unit
) {
    CardBorderInside(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        borderColor = MaterialTheme.colorScheme.background,
        borderWidth = 0.dp,
        containerColor = MaterialTheme.colorScheme.background,
        sides = listOf(),
        onClick = {
            onClick(content)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
            ) {
                ImageHandlerRes(
                    modifier = Modifier
                        .size(64.dp),
                    image = content.image!!,
                    contentDescription = ""
                )
                if (musicSelected != null && musicSelected.id == content.id) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(
                                overlay.copy(alpha = 0.2f),
                                RoundedCornerShape(18.dp)
                            )
                    )
                    ImageHandlerRes(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        image = R.drawable.frame,
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
            ) {

                TextComponent(
                    text = content.title!!,
                    type = TextType.Title,
                    size = TextSize.Medium,
                    fontWeight = FontWeight.Bold,
                    color = titleColor()
                )

                TextComponent(
                    text = content.description!!,
                    type = TextType.Body,
                    size = TextSize.Medium,
                    color = labelColor()
                )

            }

            Spacer(modifier = Modifier.width(8.dp))

            val actionType = getActionType<ActionType>(content.actionType!!)

            ImageHandlerRes(
                modifier = Modifier
                    .size(
                        when (actionType) {
                            ActionType.Option -> {
                                24.dp
                            }

                            ActionType.V1 -> {
                                40.dp
                            }
                        }
                    ),
                image = actionType.icon,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun ItemHomePlayControl(content: Music) {
    var sliderPosition by remember { mutableFloatStateOf(50f) }
    val range = 0f..100f

    CardBorderInside(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        containerColor = playerBackgroundColor(),
        sides = listOf(),
        onClick = {
        }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 2.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ImageHandlerRes(
                    modifier = Modifier
                        .size(64.dp),
                    image = content.image!!,
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                ) {

                    TextComponent(
                        text = content.title!!,
                        type = TextType.Title,
                        size = TextSize.Medium,
                        fontWeight = FontWeight.Bold,
                        color = titleColor()
                    )

                }

                Spacer(modifier = Modifier.width(8.dp))

                Row {
                    ImageHandlerRes(
                        modifier = Modifier
                            .size(22.dp),
                        image = R.drawable.frame_1261154103,
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    ImageHandlerRes(
                        modifier = Modifier
                            .size(22.dp),
                        image = R.drawable.mynaui_pause_solid,
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    ImageHandlerRes(
                        modifier = Modifier
                            .size(22.dp),
                        image = R.drawable.frame_1261154104,
                        contentDescription = ""
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
            SliderComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(horizontal = 4.dp),
                sliderValue = sliderPosition,
                sliderRange = range,
                onValueChange = { value ->
                    sliderPosition = value
                },
                onValueChangeFinished = {
                },
            )
        }
    }
}
