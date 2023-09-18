package com.example.minisoundscompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.minisoundscompose.data.StatusConfig


@Composable
fun KilledScreen(status: StatusConfig?, modifier: Modifier = Modifier) {
    if (status != null) {
        StatusMessage(status)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun KilledScreenPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxWidth()
    ) {
        KilledScreen(
            StatusConfig(
                on = false,
                title = "Upgrade BBC Sounds",
                message = "To make sure you get the best possible experience we’ve released a new version of the app. Please upgrade to continue enjoying BBC Sounds.",
                linkTitle = "Go to store",
                googleAppStoreUrl = "https://play.google.com/store/apps/details?id=com.bbc.sounds",
                amazonAppStoreUrl = "amzn://apps/android?p=com.bbc.sounds"
            )
        )
    }
}

