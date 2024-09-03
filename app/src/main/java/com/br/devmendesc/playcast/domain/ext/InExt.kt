package com.br.devmendesc.playcast.domain.ext

fun Int.formatDuration(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60

    val hoursFormatted = hours.toString().padStart(2, '0')
    val minutesFormatted = minutes.toString().padStart(2, '0')

    return "${hoursFormatted}:${minutesFormatted}"
}
