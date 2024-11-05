package com.malky.go_orders.screens.events

sealed interface RestaurantEvents {
    data class FetchRestaurantInfo(val restaurantID: Int):RestaurantEvents
    data class FetchMenuCategories(val restaurantID: Int):RestaurantEvents
    data class FetchMenuItems(val category: String):RestaurantEvents
}