package com.br.devmendesc.playcast.ui.view.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.br.devmendesc.playcast.R
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import com.br.devmendesc.playcast.domain.models.vo.PodcastVO
import com.br.devmendesc.playcast.ui.components.StarsCardsEpisodes
import com.br.devmendesc.playcast.ui.components.StarsHorizontalCardsPodCasts
import com.br.devmendesc.playcast.ui.components.StarsImageCarousel
import com.br.devmendesc.playcast.ui.components.StarsLoading
import com.br.devmendesc.playcast.ui.components.StarsSearchBar
import com.br.devmendesc.playcast.ui.components.StarsText
import com.br.devmendesc.playcast.ui.components.StarsToast
import com.br.devmendesc.playcast.ui.components.StarsVerticalSpacer
import com.br.devmendesc.playcast.ui.components.ToastType
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomePage(
    onNavPodcastDetail: (PodcastVO) -> Unit,
    onNavPlayerEpisode: (List<EpisodeVO>, Int) -> Unit
) {
    val homePageViewModel = koinViewModel<HomePageViewModel>()
    val podcastUiState = homePageViewModel.podcastUiState.collectAsState()
    val latestPodcastsUiState = homePageViewModel.latestPodcastsUiState.collectAsState()
    val latestEpisodesUiState = homePageViewModel.latestEpisodesUiState.collectAsState()

    val focusManager = LocalFocusManager.current


    LaunchedEffect(Unit) {
        homePageViewModel.latestPodcasts()
        homePageViewModel.latestEpisodes()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StarsText(
            text = stringResource(R.string.busque_pelo_seu_podcast_favorito),
            fontSize = 24,
            lineHeight = 32,
            modifier = Modifier.padding(16.dp)
        )

        StarsSearchBar(
            onSearch = { url ->
                focusManager.clearFocus()
                homePageViewModel.loadingPodcast(url)
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
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

            when (latestPodcastsUiState.value) {
                is HomePageUiState.LatestPodcastLoaded -> {
                    val podcasts =
                        (latestPodcastsUiState.value as HomePageUiState.LatestPodcastLoaded).podcastList
                    item {
                        LazyRow {
                            items(podcasts.take(10)) { podcast ->
                                StarsHorizontalCardsPodCasts(
                                    podcast = podcast,
                                    onPodcastClick = {
                                        homePageViewModel.loadingPodcast(it.link)
                                    }
                                )
                            }
                        }
                    }
                }

                is HomePageUiState.LatestPodcastLoading -> {
                    item {
                        StarsLoading()
                    }
                }

                else -> Unit
            }

            item {
                StarsVerticalSpacer(dp = 20)
                StarsText(text = stringResource(R.string.episodios_reproduzidos), fontSize = 16)
                StarsVerticalSpacer(dp = 20)
            }

            when (latestEpisodesUiState.value) {
                is HomePageUiState.LatestEpisodesLoaded -> {
                    val episodes =
                        (latestEpisodesUiState.value as HomePageUiState.LatestEpisodesLoaded).episodeList
                    items(episodes.take(5)) { episode ->
                        StarsCardsEpisodes(episode = episode, onEpisodeClick = {
                            onNavPlayerEpisode.invoke(listOf(episode), 0)
                        })
                    }
                }

                is HomePageUiState.LatestEpisodesLoading -> {
                    item {
                        StarsLoading()
                    }
                }

                else -> Unit
            }
        }
    }

    when (podcastUiState.value) {
        is HomePageUiState.PodcastLoaded -> {
            val podcast = (podcastUiState.value as HomePageUiState.PodcastLoaded).podcast
            homePageViewModel.resetPodcastState()
            onNavPodcastDetail.invoke(podcast)
        }

        is HomePageUiState.PodcastLoading -> {
            Column(modifier = Modifier.fillMaxSize()) {
                StarsLoading()
            }
        }
        
        is HomePageUiState.PodcastError -> {
            StarsToast(message = stringResource(R.string.erro_message_podcast), type = ToastType.ERROR)
            homePageViewModel.resetPodcastState()
        }

        else -> Unit
    }
}
