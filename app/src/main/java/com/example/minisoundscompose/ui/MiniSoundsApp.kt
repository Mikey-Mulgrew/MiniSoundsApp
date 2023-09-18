package com.example.minisoundscompose.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

enum class MiniSoundsApp(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Config(title = R.string.config)
}

@Composable
fun MiniSoundsApp(remoteConfigViewModel: RemoteConfigViewModel = viewModel(), navController: NavHostController = rememberNavController()) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MiniSoundsApp.valueOf(
        backStackEntry?.destination?.route ?: MiniSoundsApp.Start.name
    )

    Scaffold(
        modifier = Modifier,
        topBar = { MiniSoundsAppBar(currentScreen) }
    ) { p ->
        val uiState = remoteConfigViewModel.configUiState
        NavHost(navController = navController, startDestination = MiniSoundsApp.Start.name, modifier = Modifier.padding(p)) {
            composable(route = MiniSoundsApp.Start.name) {
                SelectConfigScreen(onConfigButtonClicked = {
                    remoteConfigViewModel.getRemoteConfig(
                        it
                    )
                    navController.navigate(MiniSoundsApp.Config.name)
                })
            }
            composable(route= MiniSoundsApp.Config.name) {
                ConfigScreen(remoteConfig = uiState)
            }
        }
    }
}




@Composable
fun MiniSoundsAppBar(currentScreen: MiniSoundsApp) {
    TopAppBar(
        modifier = Modifier
            .height(96.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        backgroundColor = Color.Black,
        title = {
            Text(
                stringResource(currentScreen.title),
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center, fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily(Font(R.font.reith_sans)),
                lineHeight = 40.sp
            )
        }
    )
}