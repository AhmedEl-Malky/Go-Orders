package com.malky.go_orders.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.malky.go_orders.screens.AuthenticationScreen
import com.malky.go_orders.screens.CartScreen
import com.malky.go_orders.screens.ExploreRestaurantsScreen
import com.malky.go_orders.screens.HomeScreen
import com.malky.go_orders.screens.RestaurantScreen
import com.malky.go_orders.viewmodels.ExploreRestaurantsViewModel
import com.malky.go_orders.viewmodels.HomeViewModel
import com.malky.go_orders.viewmodels.RestaurantViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    val navHost = NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen
    ) {

        composable<Navigation.HomeScreen> {
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                navController = navController,
                onEvent = homeViewModel::onEvent
            )
        }

        composable<Navigation.ExploreRestaurantsScreen> {
            val exploreRestaurantsViewModel: ExploreRestaurantsViewModel = hiltViewModel()
            val state by exploreRestaurantsViewModel.state.collectAsState()
            val homeState by homeViewModel.state.collectAsState()
            ExploreRestaurantsScreen(
                state = state,
                homeState = homeState,
                navController = navController,
                onEvent = exploreRestaurantsViewModel::onEvent,
                onHomeEvent = homeViewModel::onEvent
            )
        }

        composable<Navigation.RestaurantScreen> {
            val restaurantViewModel: RestaurantViewModel = hiltViewModel()
            val state by restaurantViewModel.state.collectAsState()
            val homeState by homeViewModel.state.collectAsState()

            val args = it.toRoute<Navigation.RestaurantScreen>()
            RestaurantScreen(
                restaurantID = args.restaurantID,
                state = state,
                homeState = homeState,
                navController = navController,
                onEvent = restaurantViewModel::onEvent,
                onHomeEvent = homeViewModel::onEvent
            )
        }

        composable<Navigation.AuthenticationScreen> {
            AuthenticationScreen()
        }

        composable<Navigation.CartScreen> {
            CartScreen()
        }

    }
}