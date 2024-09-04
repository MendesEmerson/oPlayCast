package com.br.devmendesc.playcast.ui.view.player

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.devmendesc.playcast.ui.components.AudioPlayer
import com.br.devmendesc.playcast.ui.theme.GrayLowTransparent
import com.br.devmendesc.playcast.ui.theme.IceWhite
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.delay

@Composable
fun AudioPlayer(
    context: Context,
    audiosUrl: List<String>,
    index: Int,
    onUpdateViewCallback: (Int) -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(index) }
    var isPlaying by remember { mutableStateOf(false) }
    val audioPlayer = remember { AudioPlayer(context) }
    var progress by remember { mutableFloatStateOf(0f) }
    var duration by remember { mutableFloatStateOf(0f) }
    var timeRemaining by remember { mutableStateOf("00:00:00") }


    LaunchedEffect(currentIndex) {
        audioPlayer.initializePlayer(audiosUrl, currentIndex)
        isPlaying = false

        audioPlayer.player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    if (currentIndex < audiosUrl.size - 1) {
                        currentIndex += 1
                        audioPlayer.next(audiosUrl, currentIndex)
                        onUpdateViewCallback.invoke(currentIndex)
                    }
                }
            }
        })
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            val currentPosition = audioPlayer.getCurrentPosition().toFloat()
            duration = audioPlayer.getDuration().toFloat()
            if (currentPosition != 0f) {
                progress = (currentPosition / duration).coerceIn(0f, 1f)
                timeRemaining = audioPlayer.getTimeRemaining()
            }
            delay(1000L)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.release()
        }
    }


    Card(
        Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GrayLowTransparent)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                enabled = currentIndex > 0,
                onClick = {
                    currentIndex -= 1
                    audioPlayer.previous(audiosUrl, currentIndex)
                    onUpdateViewCallback.invoke(currentIndex)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.SkipPrevious,
                    contentDescription = "Previous",
                    modifier = Modifier.size(80.dp),
                    tint = IceWhite
                )
            }

            IconButton(onClick = { audioPlayer.seekBackward(10) }) {
                Icon(
                    imageVector = Icons.Filled.Replay10,
                    contentDescription = "Rewind 10 seconds",
                    modifier = Modifier.size(80.dp),
                    tint = IceWhite
                )
            }

            IconButton(
                onClick = {
                    if (isPlaying) {
                        audioPlayer.pause()
                    } else {
                        audioPlayer.play()
                    }
                    isPlaying = !isPlaying
                }
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Filled.PauseCircle else Icons.Filled.PlayCircle,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    modifier = Modifier.size(80.dp),
                    tint = IceWhite
                )
            }

            IconButton(onClick = { audioPlayer.seekForward(10) }) {
                Icon(
                    imageVector = Icons.Filled.Forward10,
                    contentDescription = "Forward 10 seconds",
                    modifier = Modifier.size(80.dp),
                    tint = IceWhite
                )
            }

            IconButton(
                enabled = currentIndex < audiosUrl.size - 1,
                onClick = {
                    currentIndex += 1
                    audioPlayer.next(audiosUrl, currentIndex)
                    onUpdateViewCallback.invoke(currentIndex)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.SkipNext,
                    contentDescription = "Next",
                    modifier = Modifier.size(80.dp),
                    tint = IceWhite
                )
            }
        }


        Spacer(modifier = Modifier
            .height(16.dp)
            .padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = progress,
                valueRange = 0f..1f,
                onValueChange = { newValue ->
                    progress = newValue
                    val seekPosition = (progress * duration).toLong()
                    audioPlayer.seekTo(seekPosition)
                },
                modifier = Modifier.weight(1f)
            )

            Text(
                text = timeRemaining,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp),
                color = IceWhite
            )

        }
    }
}
