package com.example.minisoundscompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.minisoundscompose.R
import com.example.minisoundscompose.data.StatusConfig
import com.example.minisoundscompose.ui.components.AliveScreen
import com.example.minisoundscompose.ui.components.KilledScreen


@Composable
fun ConfigScreen(remoteConfig: ConfigUiState, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(start = 2.dp, end = 2.dp)
    ) {
        when (remoteConfig) {
            is ConfigUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is ConfigUiState.Success -> {
                //Text(text = remoteConfig.config.toPrettyString())
                AliveKilledScreen(remoteConfig.config.status)
            }

            is ConfigUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun AliveKilledScreen(statusConfig: StatusConfig) {
    if (statusConfig.on) AliveScreen(statusConfig) else KilledScreen(statusConfig)
}