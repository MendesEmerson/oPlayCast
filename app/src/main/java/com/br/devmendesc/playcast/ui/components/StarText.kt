package com.br.devmendesc.playcast.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.br.devmendesc.playcast.ui.theme.IceWhite

@Composable
fun StarsText(
    modifier: Modifier = Modifier,
    text: String,
    textAling: TextAlign = TextAlign.Start,
    lineHeight: Int = 16,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = IceWhite,
    maxLines: Int = Int. MAX_VALUE
) {

    Text(
        modifier = modifier,
        text = text,
        textAlign = textAling,
        color = color,
        fontSize = fontSize.sp,
        lineHeight = if (lineHeight > fontSize) lineHeight.sp else fontSize.sp,
        fontWeight = fontWeight,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines
    )
}