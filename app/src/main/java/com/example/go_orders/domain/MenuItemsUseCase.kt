package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.state.RestaurantInfoUIState.MenuItemUIState
import io.github.jan.supabase.SupabaseClient

class MenuItemsUseCase(
    val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(category:String):List<MenuItemUIState>{
        return supabaseClient.getMenuItems(category)
    }
}