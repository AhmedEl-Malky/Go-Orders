package com.malky.go_orders.domain

import com.malky.go_orders.data.Remote.GoOrdersServices
import com.malky.go_orders.data.State
import com.malky.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import com.malky.go_orders.utils.StateHandler
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