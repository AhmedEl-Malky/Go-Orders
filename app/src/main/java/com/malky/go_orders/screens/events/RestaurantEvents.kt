package com.malky.go_orders.screens.events

import com.malky.go_orders.state.RestaurantInfoUIState.MenuOptions

sealed interface RestaurantEvents {
    data class FetchRestaurantInfo(val restaurantID: Int):RestaurantEvents
    data class FetchMenuCategories(val restaurantID: Int):RestaurantEvents
    data class FetchMenuItems(val restaurantID: Int):RestaurantEvents
    data class FetchMenuImages(val restaurantID: Int):RestaurantEvents
    data class SelectOption(val option:MenuOptions):RestaurantEvents
}