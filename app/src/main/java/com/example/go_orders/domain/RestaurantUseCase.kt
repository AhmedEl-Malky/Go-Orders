package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow

class RestaurantUseCase(
    val supabaseClient : GoOrdersServices
) {
    suspend operator fun invoke(
        restaurantID :Int
    ): Flow<State<List<RestaurantUIState>>> {
        return StateHandler {
            supabaseClient.getRestaurantInfo(restaurantID)
        }
    }
}