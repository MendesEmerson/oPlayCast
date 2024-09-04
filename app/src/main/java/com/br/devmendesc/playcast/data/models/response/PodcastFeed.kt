package com.br.devmendesc.playcast.data.models.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement


@JacksonXmlRootElement(localName = "rss")
data class PodcastFeed(
    @JacksonXmlProperty(localName = "channel")
    val channel: Channel? = null
)

