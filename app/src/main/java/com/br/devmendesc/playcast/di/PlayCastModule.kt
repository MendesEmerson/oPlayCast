package com.br.devmendesc.playcast.di

import com.br.devmendesc.playcast.data.db.AppDatabase
import com.br.devmendesc.playcast.data.repository.PodcastRepository
import com.br.devmendesc.playcast.data.service.PodcastService
import com.br.devmendesc.playcast.domain.mapper.EpisodeMapper
import com.br.devmendesc.playcast.domain.mapper.PodcastMapper
import com.br.devmendesc.playcast.domain.usecases.LatestEpisodesUseCase
import com.br.devmendesc.playcast.domain.usecases.LatestPodcastsUseCase
import com.br.devmendesc.playcast.domain.usecases.LoadingPodcastUseCase
import com.br.devmendesc.playcast.ui.view.home.HomePageViewModel
import com.br.devmendesc.playcast.ui.view.podcastDetail.PodcastDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import provideRetrofit
import retrofit2.Retrofit

val playCastModule = module {

    single { provideRetrofit() }
    single { AppDatabase.getDatabase(androidContext()) }
    single { get<AppDatabase>().episodeDao() }
    single { get<AppDatabase>().podcastDao() }
    single { get<Retrofit>().create(PodcastService::class.java) }

    single { PodcastRepository(get(), get(), get(), get(), get()) }

    single { PodcastMapper() }
    single { EpisodeMapper() }

    viewModel { HomePageViewModel(get(), get(), get()) }
    viewModel { PodcastDetailViewModel(get()) }

    factory { LatestEpisodesUseCase(get()) }
    factory { LatestPodcastsUseCase(get()) }
    factory { LoadingPodcastUseCase(get()) }
}
