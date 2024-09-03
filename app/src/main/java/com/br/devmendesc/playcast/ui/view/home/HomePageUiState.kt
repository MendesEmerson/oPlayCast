package com.br.devmendesc.playcast.ui.view.home

import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import com.br.devmendesc.playcast.domain.models.vo.PodcastVO

sealed class HomePageUiState {

    data object PodcastInitial : HomePageUiState()

    data object PodcastLoading : HomePageUiState()
    data object PodcastError : HomePageUiState()
    data class PodcastLoaded(val podcast: PodcastVO) : HomePageUiState()

    data object LatestPodcastLoading : HomePageUiState()
    data object LatestPodcastError : HomePageUiState()
    data class LatestPodcastLoaded(val podcastList: List<PodcastVO>) : HomePageUiState()

    data object LatestEpisodesLoading : HomePageUiState()
    data object LatestEpisodesError : HomePageUiState()
    data class LatestEpisodesLoaded(val episodeList: List<EpisodeVO>) : HomePageUiState()

}