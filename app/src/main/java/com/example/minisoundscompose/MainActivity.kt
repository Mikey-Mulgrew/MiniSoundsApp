package com.example.minisoundscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.minisoundscompose.ui.MiniSoundsApp
import com.example.minisoundscompose.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    tonalElevation = 5.dp
                ) {
                    MiniSoundsApp()
                }
            }
        }
    }
}