package com.example.minisoundscompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisoundscompose.data.MiniSoundsHttpClient
import com.example.minisoundscompose.data.RemoteConfig
import com.example.minisoundscompose.data.JsonParser
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

sealed interface ConfigUiState {
    data class Success(val config: RemoteConfig) : ConfigUiState
    object Error : ConfigUiState
    object Loading : ConfigUiState
}

class RemoteConfigViewModel: ViewModel() {
    var configUiState: ConfigUiState by mutableStateOf(ConfigUiState.Loading)
        private set

    fun getRemoteConfig(endpoint: String = "2.3.0/config.json") {
        viewModelScope.launch {
            val client = MiniSoundsHttpClient()
            client.getString(endpoint = endpoint, callback = object:
                Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    configUiState = ConfigUiState.Error
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Returned ${response.code} with message ${response.message}")
                        val responseString = response.body?.string() ?: return
                        val parser = JsonParser()
                        val remoteConfig = parser.parseRemoteConfigJsonString(responseString)
                        configUiState = ConfigUiState.Success(remoteConfig!!)
                    }
                }
            })
        }
    }
}