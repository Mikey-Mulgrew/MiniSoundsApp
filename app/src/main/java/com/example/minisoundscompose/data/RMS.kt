package com.example.minisoundscompose.data

import com.squareup.moshi.Json

data class RMS(
    @Json(name = "data") val modules: List<Module>
)

data class Module(
    val type: String,
    val id: String,
    val title: String,
    @Json(name = "data") val playableItems: List<PlayableItem>
)

data class PlayableItem(
    val type: String = "",
    val id: String = "",
    val urn: String = "",
    val network: Network = Network(),
    val titles: Titles = Titles(),
    val synopses: Synopses = Synopses(),
    @Json(name = "image_url") val imageUrl: String = ""
)

data class Network(
    val id: String = "",
    val key: String = "",
    @Json(name = "short_title") val shortTitle: String = "",
    @Json(name = "logo_url") val logoUrl: String = ""
)

data class Titles(
    val primary: String = "",
    val secondary: String = "",
    val tertiary: String = ""
)

data class Synopses(
    val short: String = "",
    val medium: String? = "",
    val long: String? = ""
)