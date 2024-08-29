package com.br.devmendesc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.br.devmendesc.playcast.ui.theme.BackgroundApp
import com.br.devmendesc.playcast.ui.view.home.HomePage

enum class Route() {
    HOME
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .background(BackgroundApp)
                .fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = Route.HOME.name
            ) {

                composable(Route.HOME.name) {
                    HomePage()
                }

            }
        }
    }
}