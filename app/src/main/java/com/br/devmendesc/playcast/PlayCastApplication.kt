package com.br.devmendesc.playcast

import android.app.Application
import com.br.devmendesc.playcast.di.playCastModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PlayCastApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PlayCastApplication)
            modules(playCastModule)
        }
    }
}