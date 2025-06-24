package com.example.goorders.home.domain

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result

interface HomeRepository {
    suspend fun getRestaurants(cityID: Int) : Result<List<Restaurant>, RemoteError>
    suspend fun getCategories() : Result<List<Category>, RemoteError>
}