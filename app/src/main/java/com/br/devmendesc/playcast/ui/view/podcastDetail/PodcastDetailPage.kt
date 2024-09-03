package com.br.devmendesc.playcast.ui.view.podcastDetail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.br.devmendesc.playcast.domain.vo.PodcastVO
import com.br.devmendesc.playcast.ui.components.StarsCardPodcastDetail
import com.br.devmendesc.playcast.ui.components.StarsCardsEpisodes
import com.br.devmendesc.playcast.ui.components.StarsText
import com.br.devmendesc.playcast.ui.components.StarsVerticalSpacer

@Composable
fun PodcasDatailPage(
    podcast: PodcastVO
) {

    LazyColumn(
        Modifier.padding(16.dp)
    ) {
        item {
            StarsCardPodcastDetail(podcast)
            StarsVerticalSpacer(dp = 24)
            StarsText(text = "Episódios", fontSize = 20, fontWeight = FontWeight.Bold)
            StarsVerticalSpacer(dp = 12)
        }


        items(podcast.episodes.size) {
            val episode = podcast.episodes[it]
            StarsCardsEpisodes(episode = episode) {
            }

        }
    }
}