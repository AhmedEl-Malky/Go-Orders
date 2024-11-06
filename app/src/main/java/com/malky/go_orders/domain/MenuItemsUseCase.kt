package com.malky.go_orders.domain

import com.malky.go_orders.data.Remote.GoOrdersServices
import com.malky.go_orders.state.RestaurantInfoUIState.MenuItemUIState

class MenuItemsUseCase(
    val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(restaurantID:Int):List<MenuItemUIState>{
        return supabaseClient.getMenuItems(restaurantID)
    }
}