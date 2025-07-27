package com.thusee.simplepolicy.ui.claims

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thusee.simplepolicy.R
import com.thusee.simplepolicy.ui.components.CustomToolbar

@Composable
fun ClaimScreen(
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                modifier = Modifier,
                title = stringResource(R.string.claims_screen_name),
                showBackButton = showBackButton,
                onBack = onBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.claims_screen_name),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}