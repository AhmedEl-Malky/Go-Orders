package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.RestaurantUIState
import com.example.go_orders.utils.StateHandler
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllRestaurantsUseCase @Inject constructor(
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