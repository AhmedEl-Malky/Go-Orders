package com.example.goorders.mainscreen.data.repository

import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.core.domain.map
import com.example.goorders.mainscreen.data.mapper.toCity
import com.example.goorders.mainscreen.data.remote.CitiesService
import com.example.goorders.mainscreen.domain.CitiesRepository
import com.example.goorders.mainscreen.domain.City

class CitiesRepositoryImpl(
    private val service: CitiesService
): CitiesRepository {
    override suspend fun getCities(): Result<List<City>, RemoteError> {
        return service.getCities().map { DTO ->
            DTO.map { city ->
                city.toCity()
            }
        }
    }


}