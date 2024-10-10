package com.example.go_orders.domain

import com.example.go_orders.data.Supabase
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.RestaurantUIState

class GetAllRestaurantsUseCase {
    suspend operator fun invoke(searchInput:String="",isOpen:Boolean): List<RestaurantUIState> {
        return Supabase.getAllRestaurants(searchInput,isOpen).sortedBy { it.id }
    }

}