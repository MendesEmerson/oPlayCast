package com.br.devmendesc.playcast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.br.devmendesc.playcast.domain.ext.formatDuration
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import com.br.devmendesc.playcast.ui.theme.GrayLowTransparent
import com.br.devmendesc.playcast.ui.theme.IceWhite

@Composable
fun StarsCardsEpisodes(
    episode: EpisodeVO,
    onEpisodeClick: () -> Unit
) {


    Card(
        onClick = { onEpisodeClick.invoke() },
        Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = GrayLowTransparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(episode.image),
                    contentDescription = episode.title,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.8f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    StarsText(
                        text = episode.title,
                        fontSize = 14,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    if (episode.duration > 0) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = GrayLowTransparent)
                        ) {
                            Row(
                                Modifier.padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AccessTime,
                                    contentDescription = "Duração",
                                    tint = IceWhite,
                                    modifier = Modifier.size(12.dp)
                                )
                                StarsText(text = episode.duration.formatDuration(), fontSize = 12)
                            }
                        }
                    }
                }
            }

            IconButton(onClick = { onEpisodeClick.invoke() }) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    imageVector = Icons.Filled.PlayCircleFilled,
                    contentDescription = "play",
                    tint = IceWhite
                )
            }
        }
    }


}
