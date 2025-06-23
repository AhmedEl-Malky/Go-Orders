package com.example.goorders.mainscreen.data.remote

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.mainscreen.data.dto.CityDTO

interface CitiesService {
    suspend fun getCities(): Result<List<CityDTO>, RemoteError>
}