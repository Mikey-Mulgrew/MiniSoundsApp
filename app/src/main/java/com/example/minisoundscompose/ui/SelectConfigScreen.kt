package com.example.minisoundscompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                onClick = { onConfigButtonClicked("2.14.0/config.json") },
                elevation = null,
                modifier = Modifier
                    .padding(end = 2.dp)
                    .border(0.dp,Color.Transparent)
                    .semantics {
                        this.contentDescription = "Button to fetch remote alive configuration"
                    },
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.icons8_green_tick_48),
                        contentDescription = "",
                        Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(stringResource(R.string.alive), fontSize = 20.sp)
                }
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                onClick = { onConfigButtonClicked("1.15.0/config.json") },
                elevation = null,
                modifier = Modifier.semantics {
                    this.contentDescription = "Button to fetch remote killed configuration"
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.icons8_warning_48),
                        contentDescription = "",
                        Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(stringResource(R.string.killed), fontSize = 20.sp)
                }
            }
        }
    }
}