package com.br.devmendesc.playcast.data.models.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "item")
data class Item(
    @JacksonXmlProperty(localName = "title")
    val title: String = "",

    @JacksonXmlProperty(localName = "description")
    val description: String = "",

    @JacksonXmlProperty(localName = "pubDate")
    val pubDate: String = "",

    @JacksonXmlProperty(localName = "image")
    val image: Image? = null,

    @JacksonXmlProperty(localName = "itunes:title")
    val itunesTitle: String? = null,

    @JacksonXmlProperty(localName = "itunes:episodeType")
    val itunesEpisodeType: String? = null,

    @JacksonXmlProperty(localName = "author")
    val itunesAuthor: String? = null,

    @JacksonXmlProperty(localName = "summary")
    val itunesSummary: String? = null,

    @JacksonXmlProperty(localName = "encoded")
    val contentEncoded: String? = null,

    @JacksonXmlProperty(localName = "duration")
    val itunesDuration: Int? = null,

    @JacksonXmlProperty(localName = "explicit")
    val explicit: String? = null,

    @JacksonXmlProperty(localName = "guid")
    val guid: String? = null,

    @JacksonXmlProperty(localName = "enclosure")
    val enclosure: Enclosure? = null
)

@JacksonXmlRootElement(localName = "enclosure")
data class Enclosure(
    @JacksonXmlProperty(localName = "url")
    val url: String = "",

    @JacksonXmlProperty(localName = "length")
    val length: String = "",

    @JacksonXmlProperty(localName = "type")
    val type: String = ""
)
