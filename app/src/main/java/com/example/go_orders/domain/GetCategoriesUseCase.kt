package com.example.go_orders.domain

import com.example.go_orders.data.Supabase
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.CategoryUIState

class GetCategoriesUseCase {
    suspend operator fun invoke():List<CategoryUIState>{
        return Supabase.getCategories().sortedBy { it.order }
    }
}