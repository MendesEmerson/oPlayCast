package com.br.devmendesc.playcast.di

import com.br.devmendesc.playcast.domain.usecases.LatestEpisodesUseCase
import com.br.devmendesc.playcast.domain.usecases.LatestPodcastsUseCase
import com.br.devmendesc.playcast.domain.usecases.LoadingPodcastUseCase
import com.br.devmendesc.playcast.ui.view.home.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playCastModule = module {

    viewModel { HomePageViewModel(get(), get(), get()) }

    factory { LatestEpisodesUseCase() }
    factory { LatestPodcastsUseCase() }
    factory { LoadingPodcastUseCase() }

}