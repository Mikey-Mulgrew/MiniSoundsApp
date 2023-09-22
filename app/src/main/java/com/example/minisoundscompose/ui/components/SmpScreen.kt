@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.minisoundscompose.ui.components

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.minisoundscompose.R
import com.example.minisoundscompose.data.PlayableItem
import com.example.minisoundscompose.utils.buildIChefUrl
import uk.co.bbc.smpan.ProductName
import uk.co.bbc.smpan.ProductVersion
import uk.co.bbc.smpan.SMPBuilder
import uk.co.bbc.smpan.VODPlayRequestBuilder
import uk.co.bbc.smpan.media.model.MediaContentHoldingImage
import uk.co.bbc.smpan.stats.av.AVStatisticsProvider
import uk.co.bbc.smpan.stats.ui.UserInteractionStatisticsProvider
import uk.co.bbc.smpan.ui.EmbeddedWindowPresentation

@Composable
fun SmpScreen(playableItem: PlayableItem) {
    Scaffold(topBar = { SmpAppBar(playableItem.titles.primary) }) {
        val context = LocalContext.current
        val smp = SMPBuilder.create(context, "Mini Sounds", "1.0.0", object :
            UserInteractionStatisticsProvider {
            override fun trackAction(
                UIAction: UserInteractionStatisticsProvider.UIAction,
                customParams: Map<String, String>
            ) {
            }
        }).build()


        val playRequest = VODPlayRequestBuilder(
            context,
            ProductName("MiniSoundsApp"),
            ProductVersion("0.0.1")
        )
            .forVpid(playableItem.id, object : AVStatisticsProvider {})
            .with(MediaContentHoldingImage(buildIChefUrl(playableItem.imageUrl, "320", "180")))
            .withAutoplay(true)
            .build()

        val viewGroup: ViewGroup = FrameLayout(context)
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(it)){
            AndroidView(factory = {
                viewGroup.layoutParams = ViewGroup.LayoutParams(1600, 900)
                viewGroup
            }, modifier = Modifier.padding(it))
        }

        smp.embeddedPlayoutWindow(
            playRequest,
            EmbeddedWindowPresentation(
                false,
                EmbeddedWindowPresentation.FILL_VIEW_ASPECT_RATIO,
                true
            )
        ).attachToViewGroup(viewGroup)
    }
}

@Composable
fun SmpAppBar(title: String) {
    TopAppBar(modifier = Modifier
        .height(96.dp)
        .fillMaxWidth()
        .padding(12.dp),
        title = {
            Text(
                title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.reith_sans)),
                lineHeight = 40.sp,
                color = MaterialTheme.colorScheme.outline
            )
        })
}