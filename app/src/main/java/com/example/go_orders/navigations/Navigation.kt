package com.example.go_orders.navigations

sealed class Navigation(val route:String) {
    object HomeScreen: Navigation("HOME")
    object ExploreRestaurantsScreen: Navigation("EXPLORE_RESTAURANTS")
}