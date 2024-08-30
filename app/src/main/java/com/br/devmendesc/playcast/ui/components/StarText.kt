package com.br.devmendesc.playcast.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.br.devmendesc.playcast.ui.theme.GrayHigh
import com.br.devmendesc.playcast.ui.theme.IceWhite

@Composable
fun StarsText(
    modifier: Modifier = Modifier,
    text: String,
    textAling: TextAlign = TextAlign.Start,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = IceWhite
) {

    Text(
        modifier = modifier,
        text = text,
        textAlign = textAling,
        color = color,
        fontSize = fontSize.sp,
        lineHeight = fontSize.sp,
        fontWeight = fontWeight,
    )
}