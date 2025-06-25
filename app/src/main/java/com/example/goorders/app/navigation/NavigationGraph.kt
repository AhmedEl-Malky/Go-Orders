package com.example.goorders.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.goorders.home.presentation.HomeScreen
import com.example.goorders.home.presentation.HomeViewModel
import com.example.goorders.main.presentation.MainScreen
import com.example.goorders.main.presentation.MainViewModel
import com.example.goorders.restaurant.presentation.RestaurantScreen
import com.example.goorders.restaurant.presentation.RestaurantViewModel

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
            val state by mainViewModel.state.collectAsStateWithLifecycle()
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
            val state by viewModel.state.collectAsStateWithLifecycle()
            val mainState by mainViewModel.state.collectAsStateWithLifecycle()
            HomeScreen(
                state = state,
                mainState = mainState,
                onAction = viewModel::onAction,
                onMainAction = mainViewModel::onAction,
                goToRestaurant = {
                    navController.navigate(
                        Route.RestaurantScreen(
                            restaurantID = it
                        )
                    )
                }
            )
        }

        composable<Route.RestaurantScreen> {
            val viewModel: RestaurantViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val mainState by mainViewModel.state.collectAsStateWithLifecycle()

            val args = it.toRoute<Route.RestaurantScreen>()
            RestaurantScreen(
                restaurantID = args.restaurantID,
                state = state,
                mainState = mainState,
                onAction = viewModel::onAction,
                onMainAction = mainViewModel::onAction
            )
        }
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