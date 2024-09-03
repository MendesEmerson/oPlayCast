package com.br.devmendesc.playcast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.br.devmendesc.playcast.domain.vo.PodcastVO
import com.br.devmendesc.playcast.ui.theme.GrayHigh
import com.br.devmendesc.playcast.ui.theme.GrayLowTransparent
import com.br.devmendesc.playcast.ui.theme.PurpleGrey80

@Composable
fun StarsCardPodcastDetail(
    podcastVO: PodcastVO
) {
    Card(
        Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GrayLowTransparent)
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)).height(350.dp),
                painter = rememberAsyncImagePainter(model = podcastVO.image),
                contentDescription = "Imagem podcast",
                contentScale = ContentScale.Inside
            )
            StarsText(text = podcastVO.authors, color = PurpleGrey80, fontSize = 14)
            StarsText(text = podcastVO.title, fontSize = 24, fontWeight = FontWeight.Bold)
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                if (podcastVO.language.isNotEmpty()) {
                    Card(colors = CardDefaults.cardColors(containerColor = GrayHigh)) {
                        Row(
                            Modifier
                                .padding(8.dp)
                                .width(120.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            StarsText(text = podcastVO.language, fontSize = 14)
                        }
                    }
                }

               if (podcastVO.genres.isNotEmpty()){

                   Card(colors = CardDefaults.cardColors(containerColor = GrayHigh)) {
                       Row(
                           Modifier
                               .padding(8.dp)
                               .width(120.dp),
                           verticalAlignment = Alignment.CenterVertically,
                           horizontalArrangement = Arrangement.Center
                       ) {
                           StarsText(text = podcastVO.genres, fontSize = 14)
                       }
                   }
               }
            }
            StarsText(text = podcastVO.description, lineHeight = 24, textAling = TextAlign.Center)
        }
    }

}
