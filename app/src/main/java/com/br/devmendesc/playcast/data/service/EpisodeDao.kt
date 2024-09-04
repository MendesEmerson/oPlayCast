package com.br.devmendesc.playcast.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.br.devmendesc.playcast.domain.models.entities.EpisodeEntity
import com.br.devmendesc.playcast.domain.models.entities.PodcastEntity

@Dao
interface EpisodeDao {
    @Insert
    suspend fun insertEpisode(episode: EpisodeEntity)

    @Query("SELECT * FROM episodes WHERE title = :title LIMIT 1")
    suspend fun findByName(title: String): EpisodeEntity?

    @Query("DELETE FROM episodes WHERE title = :title")
    suspend fun deletePodcast(title: String)

    @Query("SELECT * FROM episodes ORDER BY id DESC LIMIT 10")
    suspend fun getLastPlayedEpisodes(): List<EpisodeEntity>?
}