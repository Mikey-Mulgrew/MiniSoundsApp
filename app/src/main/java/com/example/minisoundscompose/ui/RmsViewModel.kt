package com.example.minisoundscompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minisoundscompose.data.JsonParser
import com.example.minisoundscompose.data.MiniSoundsHttpClient
import com.example.minisoundscompose.data.PlayableItem
import com.example.minisoundscompose.data.RMS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


sealed interface RmsUiState {
    data class Success(val config: RMS) : RmsUiState
    object Error : RmsUiState
    object Loading : RmsUiState
}

class RmsViewModel : ViewModel() {
    var rmsUiState: RmsUiState by mutableStateOf(RmsUiState.Loading)
        private set

    fun getRmsData() {
        viewModelScope.launch {
            val client = MiniSoundsHttpClient()
            client.getString(
                "https://rms.api.bbc.co.uk/v2/experience/inline/stations",
                callback = object :
                    Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                        rmsUiState = RmsUiState.Error
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.use {
                            if (!response.isSuccessful) throw IOException("Returned ${response.code} with message ${response.message}")
                            val responseString = response.body?.string() ?: return
                            val parser = JsonParser()
                            val rmsData = parser.parseRmsJsonString(responseString)
                            rmsUiState = RmsUiState.Success(rmsData!!)
                        }
                    }
                })
        }
    }

    private val _rmsPlayingUiState = MutableStateFlow(PlayableItem())
    val rmsPlayingUiState: StateFlow<PlayableItem> = _rmsPlayingUiState.asStateFlow()

    fun setRmsPlaying(playableItem: PlayableItem) {
        _rmsPlayingUiState.value = playableItem
    }

}