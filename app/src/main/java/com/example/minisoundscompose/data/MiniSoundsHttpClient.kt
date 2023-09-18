package com.example.minisoundscompose.data

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val BASE_URL =
    "https://sounds-mobile-config.files.bbci.co.uk/android/"

class MiniSoundsHttpClient() {
    private val client = OkHttpClient()

    fun getString(endpoint: String, callback: Callback) {
        val request: Request = Request.Builder().url(BASE_URL + endpoint).build()

        return client.newCall(request).enqueue( callback )
    }

}