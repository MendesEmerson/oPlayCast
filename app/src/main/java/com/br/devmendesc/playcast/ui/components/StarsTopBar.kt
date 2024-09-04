package com.br.devmendesc.playcast.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.br.devmendesc.playcast.ui.theme.IceWhite

@Composable
fun StarsTopBar(
    onBack: () -> Unit
) {
    Column(
        Modifier
            .background(Color.Transparent)
            .statusBarsPadding()) {
        IconButton(onClick = { onBack.invoke() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Voltar",
                tint = IceWhite
            )
        }
    }
}