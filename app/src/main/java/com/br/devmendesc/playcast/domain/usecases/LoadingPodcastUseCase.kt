package com.br.devmendesc.playcast.domain.usecases

import android.util.Log
import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LoadingPodcastUseCase(
    private val podcastRepository: PodcastRepository,
) {
    operator fun invoke(url: String): Flow<HomePageUiState> = flow {
        runCatching {
            podcastRepository.fetchPodcast(url)

        }
            .onSuccess { podcast ->
                val podcastWithLink = podcast.apply {
                    link = url
                }
                podcastRepository.savePodcast(podcastWithLink)
                emit(HomePageUiState.PodcastLoaded(podcastWithLink))
            }
            .onFailure {
                emit(HomePageUiState.PodcastError)
            }
    }
}