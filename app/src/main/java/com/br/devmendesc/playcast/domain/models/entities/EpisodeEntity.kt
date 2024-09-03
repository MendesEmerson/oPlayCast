package com.br.devmendesc.playcast.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val duration: Int,
    val image: String,
    val category: String,
    val explicit: String,
    val urlEpisode: String,
)
