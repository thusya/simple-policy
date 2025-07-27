package com.thusee.simplepolicy.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thusee.simplepolicy.constants.AnimationConfig
import com.thusee.simplepolicy.ui.claims.ClaimScreen
import com.thusee.simplepolicy.ui.home.HomeScreen
import com.thusee.simplepolicy.ui.policies.PoliciesScreen
import com.thusee.simplepolicy.ui.navigation.Graph.BOTTOM_BAR
import com.thusee.simplepolicy.ui.profile.ProfileScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        route = BOTTOM_BAR,
        startDestination = BottomBarNavItems.Policies.route
    ) {
        composable(
            route = BottomBarNavItems.Policies.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                PoliciesScreen()
            }
        }
        composable(
            route = BottomBarNavItems.Home.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                HomeScreen()
            }
        }
        composable(
            route = BottomBarNavItems.Claims.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                ClaimScreen()
            }
        }

        composable(
            route = BottomBarNavItems.Profile.route,
            enterTransition = AnimationConfig.enterTransitionDefault,
            exitTransition = AnimationConfig.exitTransitionDefault,
        ) {
            Box(modifier = Modifier.padding(paddingValues)) {
                ProfileScreen()
            }
        }
    }
}

sealed class NavigationScreen(val route: String) {
    data object Home : NavigationScreen("Home")
    data object Policies : NavigationScreen("Policies")
    data object Claims : NavigationScreen("Claims")
    data object Profile : NavigationScreen("Profile")
}

object Graph {
    const val BOTTOM_BAR = "bottom_bar_graph"
    const val POLICIES = "policies_graph"
}