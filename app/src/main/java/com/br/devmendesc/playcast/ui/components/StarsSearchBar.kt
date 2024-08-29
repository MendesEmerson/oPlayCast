package com.br.devmendesc.playcast.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.br.devmendesc.playcast.ui.theme.GrayLow
import com.br.devmendesc.playcast.ui.theme.WhiteHigh


@Composable
fun StarsSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    placeholder: String = "Insira a URL para buscar seu podcast"
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Row(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp).background(color = WhiteHigh, shape = RoundedCornerShape(24.dp)),
            value = textState, onValueChange = { textState = it },
            singleLine = true,
            placeholder = { StarsText(text = placeholder, fontSize = 12, color = GrayLow) },
            shape = RoundedCornerShape(24.dp),
            leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Icone pesquisa",
                        tint = GrayLow
                    )
            },
            trailingIcon = {
                IconButton(onClick = { onSearch(textState.text) }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Buscar"
                    )
                }
            }
        )

    }
}