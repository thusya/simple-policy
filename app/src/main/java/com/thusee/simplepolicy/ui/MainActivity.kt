package com.thusee.simplepolicy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.thusee.simplepolicy.ui.navigation.BottomBarScreen
import com.thusee.simplepolicy.ui.theme.SimplePolicyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimplePolicyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    BottomBarScreen()
                }
            }
        }
    }
}