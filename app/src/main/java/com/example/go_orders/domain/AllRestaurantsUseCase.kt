package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AllRestaurantsUseCase @Inject constructor(
    private val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(
        searchInput: String = "",
        isOpen: Boolean,
        category: String = ""
    ): Flow<State<List<RestaurantUIState>>> {
        return StateHandler {
            supabaseClient.getAllRestaurants(searchInput, isOpen, category = if (category=="all") "" else category).sortedBy { it.id }
        }
    }
}