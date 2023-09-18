package com.example.minisoundscompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minisoundscompose.data.StatusConfig


@Composable
fun AliveScreen(status: StatusConfig?, modifier: Modifier = Modifier) {
    Column(modifier = Modifier) {
        if (status != null) {
            Box(modifier = Modifier
                .border(4.dp, Color.DarkGray, RoundedCornerShape(5.dp))
                .padding(10.dp)){
                Column {
                    Text(status.toString())
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AliveScreenPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxWidth()
    ) {
        AliveScreen(
            StatusConfig(
                on = true,
                title = "not killed",
                message = "words",
                linkTitle = "go to",
                googleAppStoreUrl = "ggl url",
                amazonAppStoreUrl = "amz url"
            )
        )
    }
}