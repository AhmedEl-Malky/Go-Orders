package com.example.go_orders.data.Remote

import com.example.go_orders.state.ExploreRestaurantsScreenUIState.*
import com.example.go_orders.state.HomeScreenUIState.CityUIState

interface GoOrdersServices {

    suspend fun getAllRestaurants(
        searchInput: String,
        isOpen: Boolean,
        category: String
    ): List<RestaurantUIState>

    suspend fun getCategories(): List<CategoryUIState>

    suspend fun getCities(): List<CityUIState>

}