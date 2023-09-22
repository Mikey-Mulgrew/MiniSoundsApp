package com.example.minisoundscompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.minisoundscompose.data.PlayableItem
import com.example.minisoundscompose.data.RMS
import com.example.minisoundscompose.ui.RmsUiState
import com.example.minisoundscompose.ui.RmsViewModel
import com.example.minisoundscompose.utils.buildIChefUrl
import com.example.minisoundscompose.utils.buildNetworkLogo


@Composable
fun AliveScreen(
    onPlayerClicked: (PlayableItem) -> Unit,
    modifier: Modifier = Modifier,
    rmsViewModel: RmsViewModel = viewModel()
) {
    val uiState by rmsViewModel.rmsUiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 2.dp, end = 2.dp)
    ) {
        when (uiState) {
            is RmsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is RmsUiState.Success -> {
                RmsContentList(rmsData = (uiState as RmsUiState.Success).config, onPlayerClicked)
            }

            is RmsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun RmsContentList(
    rmsData: RMS, onPlayerClicked: (PlayableItem) -> Unit
) {
    Column {
        LazyColumn {
            items(rmsData.modules[0].playableItems) {
                RmsItem(playableItem = it, onPlayerClicked = onPlayerClicked)
            }
        }
    }
}

@Composable
fun RmsItem(
    playableItem: PlayableItem,
    onPlayerClicked: (PlayableItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(onClickLabel = "to play ${playableItem.titles.primary}") {
                onPlayerClicked(playableItem)
            }
            .semantics { contentDescription = getContentDescription(playableItem) }) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            StationDescription(playableItem)
            Spacer(modifier = Modifier.width(30.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxHeight()
            ) {
                val img = buildIChefUrl(playableItem.imageUrl)
                AsyncImage(
                    model = img, contentDescription = "", modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun StationDescription(playableItem: PlayableItem) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .padding(6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val img = buildNetworkLogo(playableItem.network.logoUrl)
            AsyncImage(
                model = img, contentDescription = "", modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(playableItem.network.shortTitle)
        }
        Divider(modifier = Modifier.padding(vertical = 5.dp))
        Text(playableItem.titles.primary, fontSize = 15.sp, textAlign = TextAlign.Center)
        Text(playableItem.titles.secondary, fontSize = 12.sp, textAlign = TextAlign.Center)
        Text(playableItem.titles.tertiary, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

fun getContentDescription(playableItem: PlayableItem): String {
    return "${playableItem.network.shortTitle} now playing"
}