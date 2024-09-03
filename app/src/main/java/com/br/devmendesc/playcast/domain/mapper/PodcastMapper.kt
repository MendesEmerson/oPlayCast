package com.br.devmendesc.playcast.domain.mapper

import android.util.Log
import com.br.devmendesc.playcast.data.models.response.Image
import com.br.devmendesc.playcast.data.models.response.Item
import com.br.devmendesc.playcast.data.models.response.PodcastFeed
import com.br.devmendesc.playcast.domain.models.entities.PodcastEntity
import com.br.devmendesc.playcast.domain.models.vo.EpisodeVO
import com.br.devmendesc.playcast.domain.models.vo.PodcastVO

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
            genres = channel?.category?.text ?: "",
            link = channel?.link?.href ?: ""
        )
    }

    private fun mapToEpisodeVO(item: Item, image: Image?): EpisodeVO {
        val imageChanel = image?.href ?: ""
        Log.i("ITEM", "${item.enclosure?.url} ${item.itunesDuration}")
        return EpisodeVO(
            title = item.title,
            duration = item.itunesDuration ?: 0,
            image = item.image?.href ?: imageChanel,
            category = item.itunesAuthor ?: "",
            explicit = item.explicit ?: "",
            urlEpisode = urlFormatter(item.enclosure?.url ?: "")
        )
    }

    fun entityToPodcastVO(podcastEntity: PodcastEntity, episodes: List<EpisodeVO>): PodcastVO {
        return PodcastVO(
            title = podcastEntity.title,
            image = podcastEntity.image,
            episodes = episodes,
            description = podcastEntity.description,
            authors = podcastEntity.authors,
            genres = podcastEntity.genres,
            language = podcastEntity.language,
            link = podcastEntity.link
        )
    }

    fun voToPodcastEntity(podcastVO: PodcastVO): PodcastEntity {
        return PodcastEntity(
            title = podcastVO.title,
            image = podcastVO.image,
            description = podcastVO.description,
            authors = podcastVO.authors,
            genres = podcastVO.genres,
            language = podcastVO.language,
            link = podcastVO.link
        )
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

    private fun urlFormatter(url: String): String {
        return when {
            url.startsWith("https://") -> url
            url.startsWith("http://") -> "https://${url.removePrefix("http://")}"
            else -> "https://$url"
        }
    }
}
