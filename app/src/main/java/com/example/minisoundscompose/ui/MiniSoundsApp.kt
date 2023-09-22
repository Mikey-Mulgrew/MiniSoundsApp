@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.minisoundscompose.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.minisoundscompose.R
import com.example.minisoundscompose.ui.components.SmpScreen

enum class MiniSoundsApp(@StringRes val title: Int) {
    Start(title = R.string.app_name), Config(title = R.string.config), Player(title = R.string.player)
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MiniSoundsApp(
    remoteConfigViewModel: RemoteConfigViewModel = viewModel(),
    rmsViewModel: RmsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MiniSoundsApp.valueOf(
        backStackEntry?.destination?.route ?: MiniSoundsApp.Start.name
    )
    val topBarState = rememberSaveable { (mutableStateOf(true))}

    when (backStackEntry?.destination?.route) {
        MiniSoundsApp.Start.name -> {
            topBarState.value = true
        }
        MiniSoundsApp.Config.name -> {
            topBarState.value = true
        }
        MiniSoundsApp.Player.name -> {
            topBarState.value = false
        }
    }

    Scaffold(modifier = Modifier, topBar = { if(topBarState.value) MiniSoundsAppBar() }) { p ->
        val configUiState = remoteConfigViewModel.configUiState
        val rmsPlayingUiState = rmsViewModel.rmsPlayingUiState
        NavHost(
            navController = navController,
            startDestination = MiniSoundsApp.Start.name,
            modifier = Modifier.padding(p)
        ) {
            composable(route = MiniSoundsApp.Start.name) {
                SelectConfigScreen(onConfigButtonClicked = {
                    remoteConfigViewModel.getRemoteConfig(it)
                    navController.navigate(MiniSoundsApp.Config.name)
                })
            }
            composable(route = MiniSoundsApp.Config.name) {
                ConfigScreen(remoteConfig = configUiState, onPlayerClicked = {
                    rmsViewModel.setRmsPlaying(it)
                    navController.navigate(MiniSoundsApp.Player.name)
                })
            }
            composable(route = MiniSoundsApp.Player.name) {
                SmpScreen(playableItem = rmsPlayingUiState.value)
            }
        }
    }
}


@Composable
fun MiniSoundsAppBar() {
    TopAppBar(modifier = Modifier
        .height(96.dp)
        .fillMaxWidth()
        .padding(top = 20.dp),
        title = {
            Text(
                stringResource(R.string.app_name),
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