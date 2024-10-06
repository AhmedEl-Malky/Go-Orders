package com.example.go_orders.navigations

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.go_orders.screens.ExploreRestaurantsScreen
import com.example.go_orders.screens.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
){
    val navHost = NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen.route
    ) {
        composable(Navigation.HomeScreen.route) { HomeScreen(navController) }
        composable(Navigation.ExploreRestaurantsScreen.route) { ExploreRestaurantsScreen() }
    }
}