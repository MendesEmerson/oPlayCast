package com.br.devmendesc.playcast.data.service

import com.br.devmendesc.playcast.data.models.response.PodcastFeed
import retrofit2.http.GET
import retrofit2.http.Url

interface PodcastService {
    @GET
    suspend fun getPodcasts(@Url url: String): PodcastFeed
}