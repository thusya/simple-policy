package com.thusee.simplepolicy.ui.navigation

import com.thusee.simplepolicy.R

sealed class BottomBarNavItems(
    val route: String,
    val title: Int = 0,
    val icon: Int = 0,
) {
    data object Home : BottomBarNavItems(
        route = NavigationScreen.Home.route,
        title = R.string.home_screen_name,
        icon = R.drawable.ic_home,
    )

    data object Policies : BottomBarNavItems(
        route = NavigationScreen.Policies.route,
        title = R.string.policies_screen_name,
        icon = R.drawable.ic_policies,
    )

    data object Claims : BottomBarNavItems(
        route = NavigationScreen.Claims.route,
        title = R.string.claims_screen_name,
        icon = R.drawable.ic_claims,
    )

    data object Profile : BottomBarNavItems(
        route = NavigationScreen.Profile.route,
        title = R.string.profile_screen_name,
        icon = R.drawable.ic_profile,
    )
}