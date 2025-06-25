package com.example.goorders.restaurant.domain

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.home.domain.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurantInfo(restaurantId: Int): Result<Restaurant?, RemoteError>
    suspend fun getMenuCategories(restaurantId: Int): Result<List<String>, RemoteError>

    suspend fun getMenuItems(restaurantId: Int): Result<List<MenuItem>, RemoteError>

    suspend fun getMenuImages(restaurantId: Int): Result<List<MenuImage>, RemoteError>
}