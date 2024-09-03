package com.br.devmendesc.playcast.data.models.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement


@JacksonXmlRootElement(localName = "channel")
data class Channel(
    @JacksonXmlProperty(localName = "title")
    val title: String? = null,

    @JacksonXmlProperty(localName = "language")
    val language: String? = null,

    @JacksonXmlProperty(localName = "category")
    val category: Category? = null,

    @JacksonXmlProperty(localName = "description")
    val description: String? = null,

    @JacksonXmlProperty(localName = "image")
    val image: Image? = null,

    @JacksonXmlProperty(localName = "author")
    val authors: String? = null,

    @JacksonXmlProperty(localName = "link")
    val link: Link? = null,

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    val items: List<Item> = emptyList()
)

@JacksonXmlRootElement(localName = "Category")
data class Category(
    @JacksonXmlProperty(localName = "text")
    val text: String? = null,
)


@JacksonXmlRootElement(localName = "link")
data class Link(
    val href: String? = null
)
