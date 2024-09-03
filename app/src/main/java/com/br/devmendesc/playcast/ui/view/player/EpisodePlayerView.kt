package com.br.devmendesc.playcast.ui.view.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explicit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.br.devmendesc.playcast.domain.ext.formatDuration
import com.br.devmendesc.playcast.domain.vo.EpisodeVO
import com.br.devmendesc.playcast.ui.components.StarsText
import com.br.devmendesc.playcast.ui.theme.GrayHigh
import com.br.devmendesc.playcast.ui.theme.GrayLowTransparent

@Composable
fun EpisodePlayerView(
    listEpisodeVO: List<EpisodeVO>,
    index: Int
) {
    var episodeVO by remember { mutableStateOf(listEpisodeVO[index]) }
    val urls = listEpisodeVO.map { it.urlEpisode }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = GrayLowTransparent)
        ) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .height(350.dp),
                    painter = rememberAsyncImagePainter(model = episodeVO.image),
                    contentDescription = "Imagem podcast",
                    contentScale = ContentScale.Inside
                )

                StarsText(
                    text = episodeVO.title,
                    fontSize = 24,
                    fontWeight = FontWeight.Bold,
                    textAling = TextAlign.Center
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (episodeVO.explicit.lowercase() == "yes") {
                        Card(colors = CardDefaults.cardColors(containerColor = GrayHigh)) {
                            Row(
                                Modifier
                                    .padding(8.dp)
                                    .width(120.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Explicit,
                                    contentDescription = "Explicit"
                                )
                                StarsText(text = "Explicit", fontSize = 14)
                            }
                        }
                    }

                    if (episodeVO.duration.formatDuration().isNotEmpty()) {
                        Card(colors = CardDefaults.cardColors(containerColor = GrayHigh)) {
                            Row(
                                Modifier
                                    .padding(8.dp)
                                    .width(120.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                StarsText(text = episodeVO.duration.formatDuration(), fontSize = 14)
                            }
                        }
                    }
                }
            }
        }


        AudioPlayer(
            context = LocalContext.current,
            audiosUrl = urls,
            index = index,
            onUpdateViewCallback = { newIndex ->
                episodeVO = listEpisodeVO[newIndex]
            }
        )
    }
}
