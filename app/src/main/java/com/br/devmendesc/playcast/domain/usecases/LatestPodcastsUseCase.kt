package com.br.devmendesc.playcast.domain.usecases

import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LatestPodcastsUseCase(
    private val repository: PodcastRepository
) {
    operator fun invoke(): Flow<HomePageUiState> = flow {
        runCatching {
            repository.getLastSearchPodcast()
        }
            .onSuccess { response ->
                emit(HomePageUiState.LatestPodcastLoaded(response ?: listOf()))
            }
            .onFailure {
                emit(HomePageUiState.PodcastError)
            }
    }
}
