package com.example.goorders.restaurant.presentation

import com.example.goorders.restaurant.domain.MenuOptions

sealed interface RestaurantActions {
    data class GetRestaurantInfo(val restaurantID: Int) : RestaurantActions
    data class GetMenuCategories(val restaurantID: Int) : RestaurantActions
    data class GetMenuItems(val restaurantID: Int) : RestaurantActions
    data class GetMenuImages(val restaurantID: Int) : RestaurantActions
    data class OnOptionSelect(val option: MenuOptions) : RestaurantActions
}