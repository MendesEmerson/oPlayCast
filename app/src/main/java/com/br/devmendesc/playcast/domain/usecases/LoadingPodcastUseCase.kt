package com.br.devmendesc.playcast.domain.usecases

import com.br.devmendesc.playcast.domain.vo.PodcastVO
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LoadingPodcastUseCase() {
    operator fun invoke(url: String): Flow<HomePageUiState> = flow {
        runCatching {
            PodcastVO(
                title = "Tech Talks Daily",
                image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
                episodes = listOf(),
                description = "",
                authors = ""
            )
        }
            .onSuccess { response ->
                emit(HomePageUiState.PodcastLoaded(response))
            }
            .onFailure {
                emit(HomePageUiState.PodcastError)
            }
    }
}