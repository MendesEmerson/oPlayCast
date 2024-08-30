package com.br.devmendesc.playcast.domain.vo

data class PodcastVO(
    val title: String,
    val image: String,
    val episodes: List<EpisodeVO>,
    val descriotion: String,
    val authores: String
)
