package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.state.RestaurantInfoUIState.MenuCategoryUIState

class MenuCategoriesUseCase(
    val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(restaurantID:Int):List<MenuCategoryUIState>{
        return supabaseClient.getMenuCategories(restaurantID)
    }
}