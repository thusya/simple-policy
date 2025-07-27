package com.thusee.simplepolicy.ui.policies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber

@Composable
fun PoliciesScreen(
    modifier: Modifier = Modifier,
    viewModel: PoliciesViewmodel = hiltViewModel(),
) {

    val state = viewModel.policyState.collectAsStateWithLifecycle()

    Timber.d("Test value: ${state.value}")

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

    }
}