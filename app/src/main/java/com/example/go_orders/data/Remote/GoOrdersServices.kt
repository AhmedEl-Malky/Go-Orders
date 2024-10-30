package com.example.go_orders.data.Remote

import com.example.go_orders.state.ExploreRestaurantsUIState.*
import com.example.go_orders.state.HomeUIState.CityUIState
import com.example.go_orders.state.RestaurantInfoUIState
import com.example.go_orders.state.RestaurantInfoUIState.*

interface GoOrdersServices {

    suspend fun getAllRestaurants(
        searchInput: String,
        isOpen: Boolean,
        category: String
    ): List<RestaurantUIState>

    suspend fun getCategories(): List<CategoryUIState>

    suspend fun getCities(): List<CityUIState>

    suspend fun getRestaurantInfo(id:Int):List<RestaurantUIState>

    suspend fun getMenuCategories(id:Int):List<MenuCategoryUIState>

    suspend fun getMenuItems(category: String):List<MenuItemUIState>

}