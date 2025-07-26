package com.thusee.simplepolicy.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thusee.simplepolicy.ui.theme.DarkNavyBlue
import com.thusee.simplepolicy.ui.theme.spacing

@Composable
fun BottomBarScreen() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BottomNavGraph(
                navController = navController,
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarNavItems.Home,
        BottomBarNavItems.Policies,
        BottomBarNavItems.Claims,
        BottomBarNavItems.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    AnimatedVisibility(
        visible = bottomBarDestination,
        enter = fadeIn(animationSpec = tween(700)),
        exit = fadeOut(animationSpec = tween(700)),
        content = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    )
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarNavItems,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    NavigationBarItem(
        label = {
            Text(
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.small),
                text = stringResource(id = screen.title),
                style = MaterialTheme.typography.bodyLarge
            )
        },

        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = stringResource(id = screen.title),
            )
        },
        selected = selected,

        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = DarkNavyBlue,
            selectedIconColor = DarkNavyBlue,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedTextColor = MaterialTheme.colorScheme.secondary,
            indicatorColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}