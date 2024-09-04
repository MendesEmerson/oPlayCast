package com.br.devmendesc.playcast.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "podcasts")
data class PodcastEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val image: String,
    val description: String,
    val authors: String,
    val genres: String,
    val language: String,
    val link: String
)