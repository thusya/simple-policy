package com.thusee.simplepolicy.ui.policies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thusee.simplepolicy.R
import com.thusee.simplepolicy.domain.model.Policy
import com.thusee.simplepolicy.ui.common.UIState
import com.thusee.simplepolicy.ui.components.CustomToolbar
import com.thusee.simplepolicy.ui.theme.spacing
import timber.log.Timber

@Composable
fun PoliciesScreen(
    modifier: Modifier = Modifier,
    viewModel: PoliciesViewmodel = hiltViewModel(),
) {

    val state = viewModel.policyState.collectAsStateWithLifecycle()

    Timber.d("Test value: ${state.value}")

    when (val state = state.value) {
        is UIState.Loading -> {}
        is UIState.Success -> Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    CustomToolbar(
                        modifier = Modifier,
                        title = stringResource(R.string.profile_screen_name),
                        showBackButton = false,
                    )
                }
            ) { paddingValues ->
                LazyColumn(Modifier.padding(paddingValues)) {
                    items(state.data) {
                        PolicyCardItem(policy = it)
                    }
                }
            }
        }

        is UIState.Error -> {
            Timber.d("Error ${state.errorData}")
        }

        is UIState.Empty -> {}
    }
}

@Composable
fun PolicyCardItem(
    policy: Policy,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = policy.policyId,
                    style = typography.titleMedium,
                    color = Color(0xFF002C5F),
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF139B59),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .background(Color.Transparent, shape = RoundedCornerShape(6.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = policy.status,
                        color = Color(0xFF139B59),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        style = typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Policy Number",
                    value = policy.policyNumber
                )

                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Next Premium Date",
                    value = policy.nextPremiumDate
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Spacer(modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall))
            }

            ExpandedViewItem(
                isExpanded = isExpanded,
                policy = policy
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { isExpanded = !isExpanded },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFF002C5F)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = if (isExpanded) "Show Less" else "Read More",
                    color = Color(0xFF002C5F)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color(0xFF002C5F),
                )
            }
        }
    }
}

val EXPAND_ANIMATION_DURATION = 700

@Composable
fun ExpandedViewItem(
    isExpanded: Boolean,
    policy: Policy
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(
                durationMillis = EXPAND_ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(
                durationMillis = EXPAND_ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(
                durationMillis = EXPAND_ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = EXPAND_ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                4.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Start Date",
                    value = policy.startDate ?: "-"
                )
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Maturity Date",
                    value = policy.maturityDate ?: "-"
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Sum Assured",
                    value = policy.sumAssured ?: "-"
                )
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Premium Frequency",
                    value = policy.premiumFrequency ?: "-"
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Last Premium Paid",
                    value = policy.lastPremiumPaidDate ?: "-"
                )
                InfoColumn(
                    modifier = Modifier.weight(1f),
                    label = "Next Premium Amount",
                    value = policy.nextPremiumAmount ?: "-"
                )
            }
        }
    }
}

@Composable
private fun InfoColumn(
    modifier: Modifier = Modifier,
    label: String, value: String
) {
    Column(modifier = modifier.padding(12.dp)) {
        Text(text = label, style = typography.bodySmall.copy(color = Color.Gray))
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}