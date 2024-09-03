package com.br.devmendesc.playcast.data.repository

import com.br.devmendesc.playcast.data.models.response.PodcastFeed
import com.br.devmendesc.playcast.data.service.PodcastService

class PodcastRepository(
    private val podcastService: PodcastService,
) {
    suspend fun fetchPodcast(url: String): PodcastFeed {
        return podcastService.getPodcasts(url)

    }

}
