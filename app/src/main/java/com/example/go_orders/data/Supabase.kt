package com.example.go_orders.data

import com.example.go_orders.BuildConfig
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.*
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns


object Supabase : GoOrdersServices {
    val supabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.GO_ORDERS_URL,
        supabaseKey = BuildConfig.API_KEY
    ) {
//        install(Auth)
        install(Postgrest)
    }

    override suspend fun getAllRestaurants(searchInput:String,isOpen: Boolean): List<RestaurantUIState> {
        return supabaseClient.from("restaurants").select{
            filter {
                eq("open_now",isOpen)
                like("name", "%$searchInput%")
            }
        }.decodeList<RestaurantUIState>()
    }

    override suspend fun getCategories(): List<CategoryUIState> {
        return supabaseClient.from("categories").select().decodeList<CategoryUIState>()
    }

}

interface GoOrdersServices {

    suspend fun getAllRestaurants(searchInput: String,isOpen:Boolean): List<RestaurantUIState>

    suspend fun getCategories(): List<CategoryUIState>

}