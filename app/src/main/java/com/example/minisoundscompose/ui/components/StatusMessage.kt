package com.example.minisoundscompose.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minisoundscompose.data.StatusConfig

@Composable
fun StatusMessage(status: StatusConfig) {
    Box(modifier = Modifier
        .border(4.dp, Color.Red, RoundedCornerShape(5.dp))
        .padding(10.dp)){
        Column {
            Text(status.title, fontSize = 30.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            Text("${status.message}\n ${status.linkTitle} ${status.googleAppStoreUrl}", textAlign = TextAlign.Center)
        }
    }
}