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

    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.PodcastInitial)
    val uiState = _uiState.asStateFlow()

    fun loadingPodcast(url: String) {
        _uiState.value = HomePageUiState.PodcastLoading
        viewModelScope.launch {
            loadingPodcastUseCase.invoke(url).collect { result ->
                when (result) {
                    is HomePageUiState.PodcastLoaded -> {
                        _uiState.value = HomePageUiState.PodcastLoaded(result.podcast)
                    }

                    is HomePageUiState.PodcastError -> {
                        _uiState.value = HomePageUiState.PodcastError
                    }

                    else -> Unit
                }
            }
        }
    }

    fun latestPodcasts() {
        _uiState.value = HomePageUiState.LatestPodcastLoading
        viewModelScope.launch {
            latestPodcastsUseCase.invoke().collect { result ->
                when (result) {
                    is HomePageUiState.LatestPodcastLoaded -> {
                        _uiState.value = HomePageUiState.LatestPodcastLoaded(result.podcastList)
                    }

                    is HomePageUiState.LatestPodcastError -> {
                        _uiState.value = HomePageUiState.LatestPodcastError
                    }

                    else -> Unit
                }
            }
        }
    }

    fun latestEpisodes() {
        _uiState.value = HomePageUiState.LatestEpisodesLoading
        viewModelScope.launch {
            latestEpisodesUseCase.invoke().collect { result ->
                when (result) {
                    is HomePageUiState.LatestEpisodesLoaded -> {
                        _uiState.value = HomePageUiState.LatestEpisodesLoaded(result.episodeList)
                    }

                    is HomePageUiState.LatestEpisodesError -> {
                        _uiState.value = HomePageUiState.LatestEpisodesError
                    }

                    else -> Unit
                }
            }
        }
    }


}