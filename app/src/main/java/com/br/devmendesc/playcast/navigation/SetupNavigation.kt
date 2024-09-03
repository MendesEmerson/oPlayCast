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
import androidx.navigation.navArgument
import com.br.devmendesc.playcast.domain.vo.PodcastVO
import com.br.devmendesc.playcast.ui.theme.BackgroundApp
import com.br.devmendesc.playcast.ui.view.home.HomePage
import com.br.devmendesc.playcast.ui.view.podcastDetail.PodcasDatailPage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun SetupNavigation(navController: NavHostController) {

    Scaffold(
        topBar = {}
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
                        PodcasDatailPage(podcast)
                    }
                }

            }
        }
    }
}
