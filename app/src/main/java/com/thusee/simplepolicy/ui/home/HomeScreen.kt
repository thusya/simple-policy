package com.thusee.simplepolicy.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import timber.log.Timber

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewmodel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.policyState.collectAsStateWithLifecycle()

    Timber.d("Test value: ${state.value}")

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

    }
}