package com.br.devmendesc.playcast.ui.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.br.devmendesc.playcast.R
import com.br.devmendesc.playcast.domain.vo.EpisodeVO
import com.br.devmendesc.playcast.domain.vo.PodcastVO
import com.br.devmendesc.playcast.ui.components.StarsHorizontalCardsPodCasts
import com.br.devmendesc.playcast.ui.components.StarsImageCarousel
import com.br.devmendesc.playcast.ui.components.StarsSearchBar
import com.br.devmendesc.playcast.ui.components.StarsText
import com.br.devmendesc.playcast.ui.components.StarsCardsEpisodes
import com.br.devmendesc.playcast.ui.components.StarsVerticalSpacer


@Composable
fun HomePage() {

    val podcasts = listOf(
        PodcastVO(
            title = "Tech Talks Daily",
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            episodes = listOf(),
            descriotion = "",
            authores = ""
        ),
        PodcastVO(
            title = "History Extra",
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            episodes = listOf(),
            descriotion = "",
            authores = ""
        ),
        PodcastVO(
            title = "Science Vs",
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            episodes = listOf(),
            descriotion = "",
            authores = ""
        ),
        PodcastVO(
            title = "The Daily",
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            episodes = listOf(),
            descriotion = "",
            authores = ""
        ),
        PodcastVO(
            title = "Stuff You Should Know",
            image = "https://lets.events/blog/wp-content/uploads/2022/05/podcast-online-scaled.jpg",
            episodes = listOf(),
            descriotion = "",
            authores = ""
        ),
        PodcastVO(
            title = "TED Talks Daily",
            image = "https://t3.ftcdn.net/jpg/05/17/08/90/360_F_517089048_ZXTKBelIKt7AoeJIJ9ftoOpnKEz2KHPc.jpg",
            episodes = listOf(),
            descriotion = "",
            authores = ""
        )
    )

    val episodes = listOf(
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


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            StarsText(
                text = stringResource(R.string.busque_pelo_seu_podcast_favorito),
                fontSize = 24
            )
            StarsVerticalSpacer(dp = 20)
            StarsSearchBar(onSearch = {})
            StarsVerticalSpacer(dp = 30)
            StarsImageCarousel(
                images = listOf(
                    R.drawable.home03,
                    R.drawable.home01,
                    R.drawable.home04,
                    R.drawable.home02
                )
            )
            StarsVerticalSpacer(dp = 20)
            StarsText(text = stringResource(R.string.podcasts_pesquisados), fontSize = 16)
            StarsVerticalSpacer(dp = 20)
        }

        item {
            LazyRow {
                items(podcasts.take(10)) { podcast ->
                    StarsHorizontalCardsPodCasts(podcast = podcast, onPodcastClick = {})
                }
            }
        }

        item {
            StarsVerticalSpacer(dp = 20)
            StarsText(text = stringResource(R.string.episodios_reproduzidos), fontSize = 16)
            StarsVerticalSpacer(dp = 20)
        }

        items(episodes.take(5)) { episode ->
            StarsCardsEpisodes(episode = episode, onEpisodeClick = {  })
        }
    }



}