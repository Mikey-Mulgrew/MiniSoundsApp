package com.example.minisoundscompose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minisoundscompose.data.PlayableItem
import com.example.minisoundscompose.data.StatusConfig
import com.example.minisoundscompose.ui.components.AliveScreen
import com.example.minisoundscompose.ui.components.ErrorScreen
import com.example.minisoundscompose.ui.components.KilledScreen
import com.example.minisoundscompose.ui.components.LoadingScreen


@Composable
fun ConfigScreen(remoteConfig: ConfigUiState, onPlayerClicked: (PlayableItem) -> Unit, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(start = 2.dp, end = 2.dp)
    ) {
        when (remoteConfig) {
            is ConfigUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is ConfigUiState.Success -> {
                //Text(text = remoteConfig.config.toPrettyString())
                AliveKilledScreen(remoteConfig.config.status, onPlayerClicked)
            }

            is ConfigUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }
}





@Composable
fun AliveKilledScreen(statusConfig: StatusConfig, onPlayerClicked: (PlayableItem) -> Unit) {
    if (statusConfig.on) AliveScreen(onPlayerClicked = onPlayerClicked) else KilledScreen(statusConfig)
}