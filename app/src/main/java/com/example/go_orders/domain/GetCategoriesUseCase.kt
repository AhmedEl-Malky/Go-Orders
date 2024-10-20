package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.CategoryUIState
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(): List<CategoryUIState>{
        return  supabaseClient.getCategories().sortedBy { it.order }
    }
}