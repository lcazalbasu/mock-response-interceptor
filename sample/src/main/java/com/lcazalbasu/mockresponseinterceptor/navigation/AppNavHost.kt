package com.lcazalbasu.mockresponseinterceptor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lcazalbasu.mockresponseinterceptor.features.detail.ui.NewsDetailScreen
import com.lcazalbasu.mockresponseinterceptor.features.news.ui.NewsListScreen

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.NewsListScreen.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Screen.NewsListScreen.route) {
            NewsListScreen(onNavigateTo = {
                when (it) {
                    is Destinations.NewsListDestinations.ToDetail -> navController.navigate(
                        route = it.resolvedRoute,
                    ) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            })
        }

        composable(
            route = Screen.NewsDetailScreen.route,
            arguments = listOf(
                navArgument(NEWS_ID_ARG) {
                    type = NavType.LongType
                },
            ),
        ) {
            NewsDetailScreen(onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}
