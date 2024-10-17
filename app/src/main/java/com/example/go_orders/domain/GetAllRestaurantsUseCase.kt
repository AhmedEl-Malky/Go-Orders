package com.example.go_orders.domain

import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.RestaurantUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow


class GetAllRestaurantsUseCase {
    suspend operator fun invoke(
        searchInput: String = "",
        isOpen: Boolean,
        category: String = ""
    ): Flow<State<List<RestaurantUIState>>> {
        return StateHandler { Supabase.getAllRestaurants(searchInput, isOpen,category).sortedBy { it.id } }
    }
}