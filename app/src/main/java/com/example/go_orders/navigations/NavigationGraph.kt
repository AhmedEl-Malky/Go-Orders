package com.example.go_orders.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    val navHost = NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen
    ) {
        composable<Navigation.HomeScreen> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                navController = navController,
                showCityForm = homeViewModel::showCityForm,
                dismissCityForm = homeViewModel::dismissCityForm,
                onSelectCity = homeViewModel::onSelectCity,
                expandCitiesMenu = homeViewModel::expandCitiesMenu,
                collapseCitiesMenu = homeViewModel::collapseCitiesMenu
            )
        }
        composable<Navigation.ExploreRestaurantsScreen> {
            val exploreRestaurantsViewModel: ExploreRestaurantsViewModel = hiltViewModel()
            val state by exploreRestaurantsViewModel.state.collectAsState()
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeState by homeViewModel.state.collectAsState()
            ExploreRestaurantsScreen(
                state = state,
                homeState = homeState,
                navController = navController,
                getCategories = exploreRestaurantsViewModel::getCategories,
                getAllRestaurants = exploreRestaurantsViewModel::getAllRestaurants,
                startScreen = exploreRestaurantsViewModel::startScreen,
                filterOpenedRestaurants = exploreRestaurantsViewModel::filterOpenedRestaurants,
                searchForRestaurant = exploreRestaurantsViewModel::searchForRestaurant,
                showCityForm = homeViewModel::showCityForm,
                dismissCityForm = homeViewModel::dismissCityForm,
                onSelectCity = homeViewModel::onSelectCity,
                expandCitiesMenu = homeViewModel::expandCitiesMenu,
                collapseCitiesMenu = homeViewModel::collapseCitiesMenu,
                onSelectCategory = exploreRestaurantsViewModel::onSelectCategory,
            )
        }
        composable<Navigation.RestaurantScreen> {
            val restaurantViewModel: RestaurantViewModel = hiltViewModel()
            val state by restaurantViewModel.state.collectAsState()
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeState by homeViewModel.state.collectAsState()

            val args = it.toRoute<Navigation.RestaurantScreen>()
            RestaurantScreen(
                restaurantID = args.restaurantID,
                state = state,
                homeState = homeState,
                fetchRestaurantInfo = restaurantViewModel::fetchRestaurantInfo,
                fetchMenuCategories = restaurantViewModel::fetchMenuCategories,
                fetchMenuItems = restaurantViewModel::fetchMenuItems
            )
        }
    }
}