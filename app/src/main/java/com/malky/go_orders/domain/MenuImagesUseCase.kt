package com.malky.go_orders.domain

import com.malky.go_orders.data.Remote.GoOrdersServices
import com.malky.go_orders.state.RestaurantInfoUIState.MenuImageUIState
import io.github.jan.supabase.SupabaseClient

class MenuImagesUseCase(
    val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke(restaurantID:Int):List<MenuImageUIState>{
        return supabaseClient.getMenuImages(restaurantID)
    }
}