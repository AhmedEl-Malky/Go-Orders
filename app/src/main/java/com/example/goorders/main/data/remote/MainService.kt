package com.example.goorders.main.data.remote

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.main.data.dto.CityDTO

interface MainService {
    suspend fun getCities(): Result<List<CityDTO>, RemoteError>
}