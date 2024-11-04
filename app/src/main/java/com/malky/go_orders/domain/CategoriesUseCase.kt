package com.malky.go_orders.domain

import com.malky.go_orders.data.Remote.GoOrdersServices
import com.malky.go_orders.state.ExploreRestaurantsUIState.CategoryUIState
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(): List<CategoryUIState>{
        try {
            return  supabaseClient.getCategories().sortedBy { it.order }
        }catch (e:Exception){
            return emptyList()
        }
    }
}