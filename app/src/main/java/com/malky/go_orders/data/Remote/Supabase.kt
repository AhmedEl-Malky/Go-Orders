package com.malky.go_orders.data.Remote

import com.malky.go_orders.BuildConfig
import com.malky.go_orders.state.ExploreRestaurantsUIState.CategoryUIState
import com.malky.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import com.malky.go_orders.state.HomeUIState.CityUIState
import com.malky.go_orders.state.RestaurantInfoUIState.*
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns


class Supabase : GoOrdersServices {
    private val supabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.GO_ORDERS_URL,
        supabaseKey = BuildConfig.API_KEY
    ) {
//        install(Auth)
        install(Postgrest)
    }

    override suspend fun getAllRestaurants(
        searchInput: String,
        isOpen: Boolean,
        category: String
    ): List<RestaurantUIState> {
        return supabaseClient.from("restaurants").select {
            filter {
                ilike("categories", "%$category%")
                eq("open_now", isOpen)
                ilike("name", "%$searchInput%")
            }
        }.decodeList<RestaurantUIState>()
    }

    override suspend fun getCategories(): List<CategoryUIState> {
        return supabaseClient.from("categories").select().decodeList<CategoryUIState>()
    }

    override suspend fun getCities(): List<CityUIState> {
        return supabaseClient.from("cities").select().decodeList<CityUIState>()
    }

    override suspend fun getRestaurantInfo(id: Int): List<RestaurantUIState> {
        return supabaseClient.from("restaurants").select {
            filter {
                eq("id", id)
            }
        }.decodeList<RestaurantUIState>()
    }

    override suspend fun getMenuCategories(id: Int): List<MenuCategoryUIState> {
        return supabaseClient.from("menuItems").select(columns = Columns.list("category")) {
            filter {
                eq("restaurantId", id)
            }
        }.decodeList<MenuCategoryUIState>()
    }

    override suspend fun getMenuItems(category: String): List<MenuItemUIState> {
        return supabaseClient.from("menuItems").select{
            filter {
                like("category", category)
            }
        }.decodeList<MenuItemUIState>()
    }
}

