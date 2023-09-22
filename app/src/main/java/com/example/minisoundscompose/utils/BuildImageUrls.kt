package com.example.minisoundscompose.utils

fun buildIChefUrl(imageUrl: String, width:String = "320", height:String = "320"): String {
    return imageUrl.replace("{recipe}", "${width}x${height}")
}

fun buildNetworkLogo(imageUrl: String): String {
    return imageUrl
        .replace("{type}", "colour")
        .replace("{size}", "276x276")
        .replace("{format}", "png")
}