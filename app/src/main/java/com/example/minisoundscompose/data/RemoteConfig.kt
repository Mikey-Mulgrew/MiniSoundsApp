package com.example.minisoundscompose.data

fun RemoteConfig.toPrettyString(indentSize: Int = 2) = " ".repeat(indentSize).let { indent ->
    toString()
        .replace(", ", ",\n$indent")
        .replace("(", "(\n$indent")
        .dropLast(1) + "\n)"
}

data class RemoteConfig(
    val status: StatusConfig,
    val rmsConfig: RmsConfig
)

data class StatusConfig(
    val on: Boolean,
    val title: String,
    val message: String,
    val linkTitle: String,
    val googleAppStoreUrl: String,
    val amazonAppStoreUrl: String
)

data class RmsConfig(
    val apiKey: String,
    val rootUrl: String
)
