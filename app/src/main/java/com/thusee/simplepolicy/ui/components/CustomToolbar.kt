package com.thusee.simplepolicy.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thusee.simplepolicy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    modifier: Modifier,
    title: String,
    showBackButton: Boolean = false,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = stringResource(id = R.string.cont_desc_back_arrow)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_round),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(32.dp),
                    tint = Color.Unspecified
                )
            }
        }
    )
}

@Preview
@Composable
fun CustomToolbarWithoutBackPreview() {
    CustomToolbar(
        modifier = Modifier,
        title = "Policies",
    )
}

@Preview
@Composable
fun CustomToolbarWithBackPreview() {
    CustomToolbar(
        modifier = Modifier,
        title = "Policies",
        showBackButton = true
    )
}
