package com.example.go_orders.data

import com.example.go_orders.BuildConfig
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.*
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from


object Supabase:GoOrdersServices {
    val supabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.GO_ORDERS_URL,
        supabaseKey = BuildConfig.API_KEY
    ) {
//        install(Auth)
        install(Postgrest)
    }
    override suspend fun getAllRestaurants(): List<RestaurantUIState> {
        return supabaseClient.from("restaurants").select().decodeList<RestaurantUIState>()
    }

}

interface GoOrdersServices{
    suspend fun getAllRestaurants(): List<RestaurantUIState>
}