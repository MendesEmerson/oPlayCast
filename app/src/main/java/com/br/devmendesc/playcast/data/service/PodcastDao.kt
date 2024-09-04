package com.br.devmendesc.playcast.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.br.devmendesc.playcast.domain.models.entities.PodcastEntity

@Dao
interface PodcastDao {

    @Insert
    suspend fun insertPodcast(podcast: PodcastEntity)

    @Query("SELECT * FROM podcasts WHERE title = :title LIMIT 1")
    suspend fun findByName(title: String): PodcastEntity?

    @Query("DELETE FROM podcasts WHERE title = :title")
    suspend fun deletePodcast(title: String)

    @Query("SELECT * FROM podcasts ORDER BY id DESC LIMIT 10")
    suspend fun getLastSearchPodcast(): List<PodcastEntity>?
}
