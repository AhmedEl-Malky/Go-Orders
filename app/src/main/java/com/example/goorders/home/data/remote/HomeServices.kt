package com.example.goorders.home.data.remote

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.home.data.dto.CategoryDTO
import com.example.goorders.home.data.dto.RestaurantDTO

interface HomeServices {
    suspend fun getRestaurants(cityID: Int): Result<List<RestaurantDTO>, RemoteError>
    suspend fun getCategories(): Result<List<CategoryDTO>, RemoteError>
}