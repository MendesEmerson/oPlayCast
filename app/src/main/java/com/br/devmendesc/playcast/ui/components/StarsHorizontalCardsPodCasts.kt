package com.br.devmendesc.playcast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun StarsHorizontalCardsPodCasts(
    podcast: PodcastVO,
    onPodcastClick: (PodcastVO) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .width(80.dp)
                .height(130.dp)
                .padding(top = 12.dp)
                .clickable { onPodcastClick(podcast) },
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                painter = rememberAsyncImagePainter(podcast.image),
                contentDescription = podcast.title,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            StarsVerticalSpacer(dp = 10)
            StarsText(
                text = podcast.title,
                fontSize = 12,
                textAling = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

