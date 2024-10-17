package com.example.go_orders.domain

import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.CategoryUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase {
    suspend operator fun invoke(): Flow<State<List<CategoryUIState>>>{
        return  StateHandler { Supabase.getCategories().sortedBy { it.order } }
    }
}