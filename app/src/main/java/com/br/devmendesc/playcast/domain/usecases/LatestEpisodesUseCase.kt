package com.br.devmendesc.playcast.domain.usecases

import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LatestEpisodesUseCase(
    private val repository: PodcastRepository
) {
    operator fun invoke(): Flow<HomePageUiState> = flow {
        runCatching {
            repository.getLastPlayedEpisodes()
        }
            .onSuccess { response ->
                emit(HomePageUiState.LatestEpisodesLoaded(response ?: listOf()))
            }
            .onFailure {
                emit(HomePageUiState.LatestEpisodesError)
            }
    }
}