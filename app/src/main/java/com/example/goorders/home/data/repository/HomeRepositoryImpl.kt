package com.example.goorders.home.data.repository

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.core.domain.map
import com.example.goorders.home.data.mappers.toCategory
import com.example.goorders.home.data.mappers.toRestaurant
import com.example.goorders.home.data.remote.HomeServices
import com.example.goorders.home.domain.Category
import com.example.goorders.home.domain.HomeRepository
import com.example.goorders.home.domain.Restaurant

class HomeRepositoryImpl(
    private val service: HomeServices
) : HomeRepository {
    override suspend fun getRestaurants(cityID: Int): Result<List<Restaurant>, RemoteError> {
        return service.getRestaurants(cityID).map { DTO ->
            DTO.map{ restaurantDTO ->
                restaurantDTO.toRestaurant()
            }
        }
    }

    override suspend fun getCategories(): Result<List<Category>, RemoteError> {
        return service.getCategories().map { DTO ->
            DTO.map { categoryDTO ->
                categoryDTO.toCategory()
            }.sortedBy { it.order }
        }
    }
}