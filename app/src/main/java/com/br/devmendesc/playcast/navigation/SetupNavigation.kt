package com.br.devmendesc.playcast.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.br.devmendesc.playcast.ui.components.StarsTopBar
import com.br.devmendesc.playcast.ui.theme.BackgroundApp
import com.br.devmendesc.playcast.ui.view.home.HomePage
import com.br.devmendesc.playcast.ui.view.player.EpisodePlayerView
import com.br.devmendesc.playcast.ui.view.podcastDetail.PodcasDatailPage

@Composable
fun SetupNavigation(navController: NavHostController) {

    @Composable
    fun topBarVerification(): Boolean {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        return navBackStackEntry.value?.destination?.route != Routes.HOME.name
    }

    Scaffold(
        topBar = {
            if (topBarVerification()) StarsTopBar(onBack = { navController.popBackStack() })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(BackgroundApp)
                .fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = Routes.HOME.name
            ) {

                composable(Routes.HOME.name) {
                    HomePage(
                        onNavPodcastDetail = { podcast ->
                            val encodedPodcast = encodePodcast(podcast)
                            navController.navigate("${Routes.PODCAST_DETAIL.name}/$encodedPodcast")
                        },
                        onNavPlayerEpisode = { episodes, index ->
                            val encodedEpisode = encodeEpisodes(episodes)
                            navController.navigate("${Routes.EPISODE_PLAYER.name}/$encodedEpisode/$index")
                        }
                    )
                }

                composable(
                    route = "${Routes.PODCAST_DETAIL.name}/{podcast}",
                    arguments = listOf(
                        navArgument("podcast") {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->
                    val podcastJson = backStackEntry.arguments?.getString("podcast")
                    val podcast = podcastJson?.let { decodePodcast(it) }
                    if (podcast != null) {
                        PodcasDatailPage(
                            podcast = podcast,
                            onNavPlayerEpisode = { episodes, index ->
                                val encodedEpisode = encodeEpisodes(episodes)
                                navController.navigate("${Routes.EPISODE_PLAYER.name}/$encodedEpisode/$index")
                            }
                        )
                    }
                }

                composable(
                    route = "${Routes.EPISODE_PLAYER.name}/{episodes}/{index}",
                    arguments = listOf(
                        navArgument("episodes") { type = NavType.StringType },
                        navArgument("index") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val episodeJson = backStackEntry.arguments?.getString("episodes")
                    val index = backStackEntry.arguments?.getInt("index")
                    val episode = episodeJson?.let { decodeEpisodes(it) }
                    if (episode != null) {
                        EpisodePlayerView(
                            listEpisodeVO = episode,
                            index = index ?: 0
                        )
                    }
                }
            }
        }
    }
}



