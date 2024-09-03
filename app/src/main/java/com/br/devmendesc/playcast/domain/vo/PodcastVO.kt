package com.br.devmendesc.playcast.domain.vo

import kotlinx.serialization.Serializable

@Serializable
data class PodcastVO(
    val title: String,
    val image: String,
    val episodes: List<EpisodeVO>,
    val description: String,
    val authors: String,
    val genres: String,
    val language: String
)
