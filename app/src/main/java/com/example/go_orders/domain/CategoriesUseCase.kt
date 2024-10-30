package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.state.ExploreRestaurantsUIState.CategoryUIState
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(): List<CategoryUIState>{
        return  supabaseClient.getCategories().sortedBy { it.order }
    }
}