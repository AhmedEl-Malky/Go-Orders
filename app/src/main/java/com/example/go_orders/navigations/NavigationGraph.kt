package com.example.go_orders.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.go_orders.screens.ExploreRestaurantsScreen
import com.example.go_orders.screens.HomeScreen
import com.example.go_orders.screens.RestaurantScreen
import com.example.go_orders.viewmodels.ExploreRestaurantsViewModel
import com.example.go_orders.viewmodels.HomeViewModel
import com.example.go_orders.viewmodels.RestaurantViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    val exploreRestaurantsViewModel: ExploreRestaurantsViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val restaurantViewModel: RestaurantViewModel = hiltViewModel()

    val navHost = NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen
    ) {
        composable<Navigation.HomeScreen> {
            HomeScreen(
                viewModel = homeViewModel,
                navController = navController
            )
        }
        composable<Navigation.ExploreRestaurantsScreen> {
            ExploreRestaurantsScreen(
                viewModel = exploreRestaurantsViewModel,
                homeViewModel = homeViewModel,
                navController = navController
            )
        }
        composable<Navigation.RestaurantScreen> {
            val args = it.toRoute<Navigation.RestaurantScreen>()
            RestaurantScreen(
                restaurantID = args.restaurantID,
                viewModel = restaurantViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}