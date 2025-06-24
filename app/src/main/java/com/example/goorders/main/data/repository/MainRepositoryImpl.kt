package com.example.goorders.main.data.repository

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.core.domain.map
import com.example.goorders.main.data.mapper.toCity
import com.example.goorders.main.data.remote.MainService
import com.example.goorders.main.domain.MainRepository
import com.example.goorders.main.domain.City

class MainRepositoryImpl(
    private val service: MainService
): MainRepository {
    override suspend fun getCities(): Result<List<City>, RemoteError> {
        return service.getCities().map { DTO ->
            DTO.map { city ->
                city.toCity()
            }
        }
    }


}