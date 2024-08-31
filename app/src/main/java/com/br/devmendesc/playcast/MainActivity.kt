package com.br.devmendesc.playcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.br.devmendesc.Navigation
import com.br.devmendesc.playcast.di.playCastModule
import com.br.devmendesc.playcast.ui.theme.PlayCastTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(playCastModule)
        }
        enableEdgeToEdge()
        setContent {
            PlayCastTheme {
                Navigation()
            }
        }
    }
}
