package com.br.devmendesc.playcast.domain.usecases

import com.br.devmendesc.playcast.domain.vo.EpisodeVO
import com.br.devmendesc.playcast.ui.view.home.HomePageUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class LatestEpisodesUseCase() {
    private val episodes = listOf(
        EpisodeVO(
            title = "Introdução ao Kotlin",
            duration = 30,
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            category = "Tecnologia"
        ),
        EpisodeVO(
            title = "História do Android",
            duration = 45,
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            category = "Tecnologia"
        ),
        EpisodeVO(
            title = "Novidades do Compose",
            duration = 35,
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            category = "Desenvolvimento"
        ),
        EpisodeVO(
            title = "Boas práticas em programação",
            duration = 40,
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            category = "Desenvolvimento"
        ),
        EpisodeVO(
            title = "Introdução ao Jetpack",
            duration = 50,
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            category = "Tecnologia"
        ),
        EpisodeVO(
            title = "Testes unitários em Kotlin",
            duration = 25,
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            category = "Desenvolvimento"
        ),
        EpisodeVO(
            title = "Arquitetura MVVM",
            duration = 55,
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            category = "Desenvolvimento"
        ),
        EpisodeVO(
            title = "Coroutines no Kotlin",
            duration = 60,
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            category = "Tecnologia"
        ),
        EpisodeVO(
            title = "Dicas de produtividade",
            duration = 20,
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            category = "Produtividade"
        ),
        EpisodeVO(
            title = "Entrevista com um desenvolvedor",
            duration = 70,
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            category = "Entrevista"
        )
    )
    operator fun invoke(): Flow<HomePageUiState> = flow {
        runCatching {
            episodes
        }
            .onSuccess { response ->
                emit(HomePageUiState.LatestEpisodesLoaded(response))
            }
            .onFailure {
                emit(HomePageUiState.LatestEpisodesError)
            }
    }
}