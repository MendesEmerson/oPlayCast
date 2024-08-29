package com.br.devmendesc.playcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.br.devmendesc.Navigation
import com.br.devmendesc.playcast.ui.theme.PlayCastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayCastTheme {
                Navigation()
            }
        }
    }
}
