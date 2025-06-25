package com.example.goorders.restaurant.data.remote

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.home.data.dto.RestaurantDTO
import com.example.goorders.restaurant.data.dto.MenuCategoryDTO
import com.example.goorders.restaurant.data.dto.MenuImageDTO
import com.example.goorders.restaurant.data.dto.MenuItemDTO

interface RestaurantService {
    suspend fun getRestaurantInfo(restaurantId: Int): Result<List<RestaurantDTO>, RemoteError>

    suspend fun getMenuCategories(restaurantId: Int): Result<List<MenuCategoryDTO>, RemoteError>

    suspend fun getMenuItems(restaurantId: Int): Result<List<MenuItemDTO>, RemoteError>

    suspend fun getMenuImages(restaurantId: Int): Result<List<MenuImageDTO>, RemoteError>
}