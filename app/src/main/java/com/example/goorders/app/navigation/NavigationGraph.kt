package com.example.goorders.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.goorders.mainscreen.presentation.MainScreen
import com.example.goorders.mainscreen.presentation.MainViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    val homeViewModel: MainViewModel = hiltViewModel()

    val navHost = NavHost(
        navController = navController,
        startDestination = Navigation.MainScreen
    ) {
        composable<Navigation.MainScreen> {
            val state by homeViewModel.state.collectAsState()
            MainScreen(
                state = state,
                onAction = homeViewModel::onAction,
                navigateToHome = {},
                navigateToCart = {}
            )
        }

//        composable<Navigation.ExploreRestaurantsScreen> {
//            val exploreRestaurantsViewModel: ExploreRestaurantsViewModel = hiltViewModel()
//            val state by exploreRestaurantsViewModel.state.collectAsState()
//            val homeState by homeViewModel.state.collectAsState()
//            ExploreRestaurantsScreen(
//                state = state,
//                homeState = homeState,
//                navController = navController,
//                onEvent = exploreRestaurantsViewModel::onEvent,
//                onHomeEvent = homeViewModel::onEvent
//            )
//        }
//
//        composable<Navigation.RestaurantScreen> {
//            val restaurantViewModel: RestaurantViewModel = hiltViewModel()
//            val state by restaurantViewModel.state.collectAsState()
//            val homeState by homeViewModel.state.collectAsState()
//
//            val args = it.toRoute<Navigation.RestaurantScreen>()
//            RestaurantScreen(
//                restaurantID = args.restaurantID,
//                state = state,
//                homeState = homeState,
//                navController = navController,
//                onEvent = restaurantViewModel::onEvent,
//                onHomeEvent = homeViewModel::onEvent
//            )
//        }
//
//        composable<Navigation.AuthenticationScreen> {
//            AuthenticationScreen()
//        }
//
//        composable<Navigation.CartScreen> {
//            CartScreen()
//        }

    }
}