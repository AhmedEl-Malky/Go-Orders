package com.malky.go_orders.navigations

import kotlinx.serialization.Serializable

object Navigation {

    @Serializable
    object HomeScreen

    @Serializable
    object ExploreRestaurantsScreen

    @Serializable
    data class RestaurantScreen(
        val restaurantID: Int,
    )

    @Serializable
    object AuthenticationScreen

    @Serializable
    object CartScreen

}