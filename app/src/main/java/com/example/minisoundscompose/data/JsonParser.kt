package com.example.minisoundscompose.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonParser {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseRemoteConfigJsonString(jsonString: String): RemoteConfig? {
        val adapter = moshi.adapter(RemoteConfig::class.java).lenient()
        return adapter.fromJson(jsonString)
    }

    fun parseRmsJsonString(jsonString: String): RMS? {
        val adapter = moshi.adapter(RMS::class.java).lenient()
        return adapter.fromJson(jsonString)
    }
}