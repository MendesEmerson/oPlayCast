package com.br.devmendesc.playcast.data.models.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "image")
data class Image(
    @JacksonXmlProperty(localName = "href")
    val href: String? = null
)