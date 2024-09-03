package com.br.devmendesc.playcast.domain.mapper

import com.br.devmendesc.playcast.data.models.response.Image
import com.br.devmendesc.playcast.data.models.response.Item
import com.br.devmendesc.playcast.data.models.response.PodcastFeed
import com.br.devmendesc.playcast.domain.vo.EpisodeVO
import com.br.devmendesc.playcast.domain.vo.PodcastVO

class PodcastMapper {

    fun mapToPodcastVO(podcastFeed: PodcastFeed): PodcastVO {
        val channel = podcastFeed.channel

        val episodes = (channel?.items?.reversed())?.map { mapToEpisodeVO(it, channel.image) }

        return PodcastVO(
            title = channel?.title ?: "",
            image =  channel?.image?.href ?: "",
            episodes = episodes ?: listOf(),
            description = channel?.description ?: "" ,
            authors = channel?.authors ?: "",
            language = getLanguageNameInOriginalLanguage(channel?.language) ?: "",
            genres = channel?.category?.text ?: ""
        )
    }

    private fun mapToEpisodeVO(item: Item, image: Image?): EpisodeVO {
        val imageChanel = image?.href ?: ""
        return EpisodeVO(
            title = item.title,
            duration = formatDuration(item.itunesDuration ?: 0),
            image = item.image?.href ?: imageChanel,
            category = item.itunesTitle ?: "Unknown",
            explicit = item.explicit ?: ""
        )
    }

    private fun formatDuration(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60

        val hoursFormatted = hours.toString().padStart(2, '0')
        val minutesFormatted = minutes.toString().padStart(2, '0')

        return "${hoursFormatted}h${minutesFormatted}"
    }

    private fun getLanguageNameInOriginalLanguage(abbreviation: String?): String? {
        val languageMap = mapOf(
            "en" to "English",
            "es" to "Español",
            "pt" to "Português",
            "fr" to "Français",
            "de" to "Deutsch",
            "it" to "Italiano",
            "ru" to "Русский",
            "ja" to "日本語",
            "zh" to "中文",
            "ar" to "العربية",
            "ko" to "한국어"

        )

        return languageMap[abbreviation] ?: abbreviation
    }
}
