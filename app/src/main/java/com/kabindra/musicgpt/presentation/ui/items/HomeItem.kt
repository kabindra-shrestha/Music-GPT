package com.kabindra.musicgpt.presentation.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabindra.musicgpt.R
import com.kabindra.musicgpt.domain.model.Music
import com.kabindra.musicgpt.presentation.ui.component.CardBorderInside
import com.kabindra.musicgpt.presentation.ui.component.ImageHandlerRes
import com.kabindra.musicgpt.presentation.ui.component.TextComponent

@Composable
fun ItemHomeMusic(
    content: Music,
    onClick: (Music) -> Unit
) {
    CardBorderInside(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clickable {
                onClick(content)
            },
        sides = listOf(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ImageHandlerRes(
                modifier = Modifier.padding(start = 4.dp, end = 2.dp),
                image = content.image!!,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextComponent(
                text = content.title!!
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextComponent(
                text = content.description
            )
        }
    }
}

/*
@Composable
fun ItemHomePlayControl(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.audio),
                            contentDescription = "Audio Icon",
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f)
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = audioTrack.title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (audioTrack.artist.isNotEmpty()) {
                                Text(
                                    text = audioTrack.artist,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W400,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                        */
/*Box(
                            modifier = Modifier
                                .height(35.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                text = (audioTrack.duration / 1000).formatToTime(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }*//*

                    }
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.previous),
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { onPrevious() },
                            contentDescription = "Previous",
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp)),
                    ) {
                        if (isPlaying) {
                            VeelIcon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .padding(4.dp),
                                id = R.drawable.pause,
                                desc = "Pause"
                            ) {
                                onPlay()
                            }
                        } else {
                            VeelIcon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .padding(4.dp),
                                id = R.drawable.play,
                                desc = "Play"
                            ) {
                                onPlay()
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.next),
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { onNext() },
                            contentDescription = "Next",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                    ) {
                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            text = currentTime,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        VeelSlider(
                            sliderValue = sliderPosition,
                            sliderRange = range,
                            onValueChange = { value ->
                                isUserDraggingSlider = true
                                sliderPosition = value
                                currentTime = sliderPosition.toLong().formatToTime()
                                onSeekChange((value * 1000).toLong())
                            },
                            onValueChangeFinished = {
                                isUserDraggingSlider = false
                                val seekToMs = (sliderPosition * 1000).toLong()
                                onSeekChangeFinished(seekToMs)
                            },
                        )
                    }

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                    ) {
                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            text = totalTime,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}*/
