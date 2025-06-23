package com.example.goorders.mainscreen.domain

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result

interface CitiesRepository {
    suspend fun getCities(): Result<List<City>, RemoteError>
}