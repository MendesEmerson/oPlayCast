package com.br.devmendesc.playcast.di

import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.data.service.PodcastService
import com.br.devmendesc.playcast.domain.mapper.PodcastMapper
import com.br.devmendesc.playcast.domain.usecases.LatestEpisodesUseCase
import com.br.devmendesc.playcast.domain.usecases.LatestPodcastsUseCase
import com.br.devmendesc.playcast.domain.usecases.LoadingPodcastUseCase
import com.br.devmendesc.playcast.ui.view.home.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import provideRetrofit
import retrofit2.Retrofit

val playCastModule = module {

    single { provideRetrofit() }
    single { get<Retrofit>().create(PodcastService::class.java) }
    single { PodcastMapper() }
    single { PodcastRepository(get()) }

    viewModel { HomePageViewModel(get(), get(), get()) }

    factory { LatestEpisodesUseCase() }
    factory { LatestPodcastsUseCase() }
    factory { LoadingPodcastUseCase(get(), get()) }

}