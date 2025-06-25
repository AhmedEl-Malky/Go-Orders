package com.example.goorders.app.navigation

import kotlinx.serialization.Serializable

object Route {

    @Serializable
    object MainScreen

    @Serializable
    object HomeScreen

    @Serializable
    data class RestaurantScreen(
        val restaurantID: Int,
    )
//
//    @Serializable
//    object AuthenticationScreen
//
//    @Serializable
//    object CartScreen

}