package com.example.minisoundscompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minisoundscompose.data.StatusConfig


@Composable
fun KilledScreen(status: StatusConfig?, modifier: Modifier = Modifier) {
    if (status != null) {
        StatusMessage(status)
    }
}

@Composable
fun StatusMessage(status: StatusConfig) {
    Box(modifier = Modifier
        .border(4.dp, Color.Red, RoundedCornerShape(5.dp))
        .padding(10.dp)){
        Column {
            Text(status.title, fontSize = 30.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Text("${status.message}\n ", textAlign = TextAlign.Center)
            val uriHandler = LocalUriHandler.current
            Button(onClick = {
                uriHandler.openUri(status.googleAppStoreUrl)
            }, modifier = Modifier.semantics { this.contentDescription = "Link to Play Store to download latest version of sounds" }) {
                Icon(Icons.Default.Send,"")
                Spacer(modifier = Modifier.width(2.dp))
                Text(status.linkTitle)
            }
        }
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

