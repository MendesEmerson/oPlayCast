package com.br.devmendesc.playcast.navigation

import com.br.devmendesc.playcast.domain.vo.PodcastVO
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun encodePodcast(podcast: PodcastVO): String {
    val podcastJson = Json.encodeToString(podcast)
    return URLEncoder.encode(podcastJson, StandardCharsets.UTF_8.toString())
}

fun decodePodcast(encodedPodcast: String): PodcastVO? {
    return try {
        val decodedJson = URLDecoder.decode(encodedPodcast, StandardCharsets.UTF_8.toString())
        Json.decodeFromString<PodcastVO>(decodedJson)
    } catch (e: Exception) {
        null
    }
}
