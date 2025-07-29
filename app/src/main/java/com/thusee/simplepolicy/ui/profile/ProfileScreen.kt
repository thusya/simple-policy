package com.thusee.simplepolicy.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thusee.simplepolicy.BuildConfig
import com.thusee.simplepolicy.R
import com.thusee.simplepolicy.constants.AppConstants
import com.thusee.simplepolicy.ui.components.CustomToolbar
import com.thusee.simplepolicy.ui.theme.spacing

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val notificationEnabled by viewModel.notificationEnabled.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CustomToolbar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.profile_screen_name),
            showBackButton = false,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                )
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            SettingHeaderItem(stringResource(id = R.string.settings_general))
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing12))
            SettingItemWithToggle(
                title = stringResource(id = R.string.setting_notification),
                checked = notificationEnabled,
                onCheckedChange = {
                    viewModel.onCheckedChange(it)
                }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing12))
            SettingItemWithArrow(stringResource(id = R.string.setting_linked_in)) {
                viewModel.openWebView(
                    AppConstants.LINKED_IN_WEB_URL,
                    context
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing12))
            SettingItemWithArrow(stringResource(id = R.string.setting_github)) {
                viewModel.openWebView(
                    AppConstants.GITHUB_WEB_URL,
                    context
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing12))
            SettingItemWithText(
                title = stringResource(id = R.string.settings_app_version),
                subtitle = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})${if (BuildConfig.DEBUG) " - Dev" else ""}"
            )
        }
    }
}

@Composable
fun SettingHeaderItem(
    title: String,
) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        ),
    )
}

@Composable
fun SettingItemWithArrow(
    title: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = MaterialTheme.shapes.extraLarge,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.spacing12,
                    bottom = MaterialTheme.spacing.spacing12,
                    start = MaterialTheme.spacing.spacing24,
                )
                .weight(1f),
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Icon(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.spacing18,
                ),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
        )
    }
}

@Composable
fun SettingItemWithText(
    title: String,
    subtitle: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = MaterialTheme.shapes.extraLarge,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.spacing12,
                    bottom = MaterialTheme.spacing.spacing12,
                    start = MaterialTheme.spacing.spacing24,
                )
                .weight(1f),
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Text(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.spacing18,
                ),
            text = subtitle,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Composable
fun SettingItemWithToggle(
    title: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface,
                shape = MaterialTheme.shapes.extraLarge,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.spacing12,
                    bottom = MaterialTheme.spacing.spacing12,
                    start = MaterialTheme.spacing.spacing24,
                )
                .weight(1f),
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            ),
        )

        Switch(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.spacing18,
                ),
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )
    }
}
