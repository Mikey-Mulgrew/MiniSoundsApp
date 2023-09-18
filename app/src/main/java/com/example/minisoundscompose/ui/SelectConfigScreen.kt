package com.example.minisoundscompose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minisoundscompose.R

@Composable
fun SelectConfigScreen(onConfigButtonClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConfigButtons(onConfigButtonClicked = onConfigButtonClicked)
    }
}


@Composable
fun ConfigButtons(onConfigButtonClicked: (String) -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { onConfigButtonClicked("2.14.0/config.json") },
                modifier = Modifier.padding(end = 2.dp)
            ) {
                Text(stringResource(R.string.alive), fontSize = 10.sp)
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                onClick = { onConfigButtonClicked("1.15.0/config.json") },
                modifier = Modifier
            ) {
                Text(stringResource(R.string.killed), fontSize = 10.sp)
            }
        }
    }
}