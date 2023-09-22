package com.example.minisoundscompose.data

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

private const val BASE_URL =
    "https://sounds-mobile-config.files.bbci.co.uk/android/"

class MiniSoundsHttpClient() {
    private val client = OkHttpClient()

    fun getString(baseUrl: String = BASE_URL, endpoint: String = "", callback: Callback) {
        val request: Request = Request.Builder().url(baseUrl + endpoint).build()

        return client.newCall(request).enqueue( callback )
    }

}