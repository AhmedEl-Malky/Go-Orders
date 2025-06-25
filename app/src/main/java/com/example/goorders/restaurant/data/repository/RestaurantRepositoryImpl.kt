package com.example.goorders.restaurant.data.repository

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.core.domain.map
import com.example.goorders.home.data.mappers.toRestaurant
import com.example.goorders.home.domain.Restaurant
import com.example.goorders.restaurant.data.mappers.toMenuCategories
import com.example.goorders.restaurant.data.mappers.toMenuImage
import com.example.goorders.restaurant.data.mappers.toMenuItem
import com.example.goorders.restaurant.data.remote.RestaurantService
import com.example.goorders.restaurant.domain.MenuImage
import com.example.goorders.restaurant.domain.MenuItem
import com.example.goorders.restaurant.domain.RestaurantRepository

class RestaurantRepositoryImpl(
    private val service: RestaurantService
) : RestaurantRepository {
    override suspend fun getRestaurantInfo(restaurantId: Int): Result<Restaurant?, RemoteError> {
        return service.getRestaurantInfo(restaurantId).map { DTO ->
            DTO.firstOrNull()?.toRestaurant()
        }
    }

    override suspend fun getMenuCategories(restaurantId: Int): Result<List<String>, RemoteError> {
        return service.getMenuCategories(restaurantId).map { DTO ->
            DTO.firstOrNull()?.categories ?: emptyList()
        }

    }

    override suspend fun getMenuItems(restaurantId: Int): Result<List<MenuItem>, RemoteError> {
        return service.getMenuItems(restaurantId).map { DTO ->
            DTO.map { menuItem ->
                menuItem.toMenuItem()
            }
        }
    }

    override suspend fun getMenuImages(restaurantId: Int): Result<List<MenuImage>, RemoteError> {
        return service.getMenuImages(restaurantId).map { DTO ->
            DTO.map { image ->
                image.toMenuImage()
            }
        }
    }
}