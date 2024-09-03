package com.br.devmendesc.playcast.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.devmendesc.playcast.domain.usecases.LatestEpisodesUseCase
import com.br.devmendesc.playcast.domain.usecases.LatestPodcastsUseCase
import com.br.devmendesc.playcast.domain.usecases.LoadingPodcastUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val loadingPodcastUseCase: LoadingPodcastUseCase,
    private val latestPodcastsUseCase: LatestPodcastsUseCase,
    private val latestEpisodesUseCase: LatestEpisodesUseCase,
) : ViewModel() {

    private val _podcastUiState = MutableStateFlow<HomePageUiState>(HomePageUiState.PodcastInitial)
    val podcastUiState = _podcastUiState.asStateFlow()

    private val _latestPodcastsUiState = MutableStateFlow<HomePageUiState>(HomePageUiState.LatestPodcastLoading)
    val latestPodcastsUiState = _latestPodcastsUiState.asStateFlow()

    private val _latestEpisodesUiState = MutableStateFlow<HomePageUiState>(HomePageUiState.LatestEpisodesLoading)
    val latestEpisodesUiState = _latestEpisodesUiState.asStateFlow()

    fun loadingPodcast(url: String) {
        val urlFormatter = urlFormatter(url)
        _podcastUiState.value = HomePageUiState.PodcastLoading
        viewModelScope.launch {
            loadingPodcastUseCase.invoke(urlFormatter).collect { result ->
                when (result) {
                    is HomePageUiState.PodcastLoaded -> {
                        _podcastUiState.value = HomePageUiState.PodcastLoaded(result.podcast)
                    }

                    is HomePageUiState.PodcastError -> {
                        _podcastUiState.value = HomePageUiState.PodcastError
                    }

                    else -> Unit
                }
            }
        }
    }

    fun latestPodcasts() {
        viewModelScope.launch {
            latestPodcastsUseCase.invoke().collect { result ->
                when (result) {
                    is HomePageUiState.LatestPodcastLoaded -> {
                        _latestPodcastsUiState.value = HomePageUiState.LatestPodcastLoaded(result.podcastList)
                    }

                    is HomePageUiState.LatestPodcastError -> {
                        _latestPodcastsUiState.value = HomePageUiState.LatestPodcastError
                    }

                    else -> Unit
                }
            }
        }
    }

    fun latestEpisodes() {
        viewModelScope.launch {
            latestEpisodesUseCase.invoke().collect { result ->
                when (result) {
                    is HomePageUiState.LatestEpisodesLoaded -> {
                        _latestEpisodesUiState.value = HomePageUiState.LatestEpisodesLoaded(result.episodeList)
                    }

                    is HomePageUiState.LatestEpisodesError -> {
                        _latestEpisodesUiState.value = HomePageUiState.LatestEpisodesError
                    }

                    else -> Unit
                }
            }
        }
    }

    fun resetPodcastState() {
        _podcastUiState.value = HomePageUiState.PodcastInitial
    }


    private fun urlFormatter(url: String): String {
        return when {
            url.startsWith("https://") -> url
            url.startsWith("http://") -> "https://${url.removePrefix("http://")}"
            else -> "https://$url"
        }
    }


}