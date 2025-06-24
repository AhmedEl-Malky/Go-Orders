package com.example.goorders.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goorders.home.presentation.HomeScreen
import com.example.goorders.home.presentation.HomeViewModel
import com.example.goorders.main.presentation.MainScreen
import com.example.goorders.main.presentation.MainViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    val mainViewModel: MainViewModel = hiltViewModel()

    val navHost = NavHost(
        navController = navController,
        startDestination = Route.MainScreen
    ) {
        composable<Route.MainScreen> {
            val state by mainViewModel.state.collectAsState()
            MainScreen(
                state = state,
                onAction = mainViewModel::onAction,
                navigateToHome = {
                    navController.navigate(Route.HomeScreen)
                },
                navigateToCart = {}
            )
        }

        composable<Route.HomeScreen> {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            val mainState by mainViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                mainState = mainState,
                onAction = viewModel::onAction,
                onMainAction = mainViewModel::onAction,
                goToRestaurant = {}
            )
        }
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