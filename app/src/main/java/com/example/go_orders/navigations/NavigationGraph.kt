package com.example.go_orders.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.go_orders.screens.ExploreRestaurantsScreen
import com.example.go_orders.screens.HomeScreen
import com.example.go_orders.viewmodels.ExploreRestaurantsViewModel
import com.example.go_orders.viewmodels.HomeViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    val exploreRestaurantsViewModel: ExploreRestaurantsViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()

    val navHost = NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen.route
    ) {
        composable(Navigation.HomeScreen.route) {
            HomeScreen(
                viewModel = homeViewModel,
                navController =navController
            )
        }
        composable(Navigation.ExploreRestaurantsScreen.route) {
            ExploreRestaurantsScreen(
                viewModel = exploreRestaurantsViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}