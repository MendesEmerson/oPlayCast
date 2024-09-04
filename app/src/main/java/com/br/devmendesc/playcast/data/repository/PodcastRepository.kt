package com.br.devmendesc.playcast.data.repository

import com.br.devmendesc.playcast.data.service.EpisodeDao
import com.br.devmendesc.playcast.data.service.PodcastDao
import com.br.devmendesc.playcast.data.service.PodcastService
import com.br.devmendesc.playcast.domain.mapper.EpisodeMapper
import com.br.devmendesc.playcast.domain.mapper.PodcastMapper
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import com.br.devmendesc.playcast.domain.models.vo.PodcastVO

class PodcastRepository(
    private val podcastService: PodcastService,
    private val episodeDao: EpisodeDao,
    private val podcastDao: PodcastDao,
    private val episodeMapper: EpisodeMapper,
    private val podcastMapper: PodcastMapper,
) {

    suspend fun fetchPodcast(url: String): PodcastVO {
        val podcast =  podcastMapper.mapToPodcastVO(podcastService.getPodcasts(url))
        return podcast
    }

    suspend fun saveEpisode(episode: EpisodeVO) {
        val episodeEntity = episodeMapper.toEntity(episode)
        val existingEpisode = episodeDao.findByName(episode.title)
        if (existingEpisode != null) episodeDao.deletePodcast(existingEpisode.title)
        episodeDao.insertEpisode(episodeEntity)
    }

    suspend fun getLastPlayedEpisodes(): List<EpisodeVO>? {
        val episodeEntities = episodeDao.getLastPlayedEpisodes()
        return episodeEntities?.map { episodeMapper.toVO(it) }
    }

    suspend fun getLastSearchPodcast(): List<PodcastVO>? {
        val podcastEntities = podcastDao.getLastSearchPodcast()
        return podcastEntities?.map { podcastMapper.entityToPodcastVO(it, listOf()) }
    }

    suspend fun savePodcast(podcast: PodcastVO) {
        val podcastEntity = podcastMapper.voToPodcastEntity(podcast)
        val existingPodcast = podcastDao.findByName(podcastEntity.title)
        if (existingPodcast != null) podcastDao.deletePodcast(existingPodcast.title)
        podcastDao.insertPodcast(podcastEntity)
    }

}

