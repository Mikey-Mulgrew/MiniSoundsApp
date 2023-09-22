package com.example.minisoundscompose.utils

fun buildIChefUrl(imageUrl: String): String {
    return imageUrl.replace("{recipe}", "320x320")
}

fun buildNetworkLogo(imageUrl: String): String {
    return imageUrl
        .replace("{type}", "colour")
        .replace("{size}", "276x276")
        .replace("{format}", "png")
}