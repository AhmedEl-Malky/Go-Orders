package com.example.goorders.main.domain

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result

interface MainRepository {
    suspend fun getCities(): Result<List<City>, RemoteError>
}