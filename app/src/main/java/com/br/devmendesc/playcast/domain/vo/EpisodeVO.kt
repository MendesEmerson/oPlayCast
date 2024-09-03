package com.br.devmendesc.playcast.domain.vo

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeVO(
    val title: String,
    val duration: String,
    val image: String,
    val category: String,
    val explicit: String
)
