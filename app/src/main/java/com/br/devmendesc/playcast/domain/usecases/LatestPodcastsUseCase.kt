package com.br.devmendesc.playcast.domain.usecases

import com.br.devmendesc.playcast.domain.vo.PodcastVO
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LatestPodcastsUseCase() {
    private val podcasts = listOf(
        PodcastVO(
            title = "Tech Talks Daily",
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            episodes = listOf(),
            description = "",
            authors = ""
        ),
        PodcastVO(
            title = "History Extra",
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            episodes = listOf(),
            description = "",
            authors = ""
        ),
        PodcastVO(
            title = "Science Vs",
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            episodes = listOf(),
            description = "",
            authors = ""
        ),
        PodcastVO(
            title = "The Daily",
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            episodes = listOf(),
            description = "",
            authors = ""
        ),
        PodcastVO(
            title = "Stuff You Should Know",
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            episodes = listOf(),
            description = "",
            authors = ""
        ),
        PodcastVO(
            title = "TED Talks Daily",
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            episodes = listOf(),
            description = "",
            authors = ""
        )
    )
    operator fun invoke(): Flow<HomePageUiState> = flow {
        runCatching {
            podcasts
        }
            .onSuccess { response ->
                emit(HomePageUiState.LatestPodcastLoaded(response))
            }
            .onFailure {
                emit(HomePageUiState.PodcastError)
            }
    }
}