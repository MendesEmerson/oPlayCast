package com.br.devmendesc.playcast.domain.usecases

import android.util.Log
import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.domain.mapper.PodcastMapper
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LoadingPodcastUseCase(
    private val podcastRepository: PodcastRepository,
    private val podcastMapper: PodcastMapper

) {
    operator fun invoke(url: String): Flow<HomePageUiState> = flow {
        runCatching {
           podcastRepository.fetchPodcast(url)
        }
            .onSuccess { podcastFeed ->
                Log.d("Response", "Response: ${podcastFeed.channel?.items?.get(1)}")
                val podcastVO = podcastMapper.mapToPodcastVO(podcastFeed)
                emit(HomePageUiState.PodcastLoaded(podcastVO))
            }
            .onFailure {throwable ->
                Log.e("LoadingPodcastUseCase", "Failed to load podcast", throwable)
                emit(HomePageUiState.PodcastError)
            }
    }
}