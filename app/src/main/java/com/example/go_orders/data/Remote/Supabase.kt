package com.example.go_orders.data.Remote

import com.example.go_orders.BuildConfig
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.*
import com.example.go_orders.state.HomeScreenUIState.CityUIState
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from



class Supabase : GoOrdersServices {
    private val supabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.GO_ORDERS_URL,
        supabaseKey = BuildConfig.API_KEY
    ) {
//        install(Auth)
        install(Postgrest)
    }

    override suspend fun getAllRestaurants(searchInput:String,isOpen: Boolean,category: String): List<RestaurantUIState> {
        return supabaseClient.from("restaurants").select{
            filter {
                like("categories","%$category%")
                eq("open_now",isOpen)
                like("name", "%$searchInput%")
            }
        }.decodeList<RestaurantUIState>()
    }

    override suspend fun getCategories(): List<CategoryUIState> {
        return supabaseClient.from("categories").select().decodeList<CategoryUIState>()
    }

    override suspend fun getCities(): List<CityUIState> {
        return supabaseClient.from("cities").select().decodeList<CityUIState>()
    }

}
