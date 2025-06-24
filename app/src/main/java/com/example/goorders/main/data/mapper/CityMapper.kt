package com.example.goorders.main.data.mapper

import com.example.goorders.main.data.dto.CityDTO
import com.example.goorders.main.domain.City

fun CityDTO.toCity(): City{
    return City(
        id = this.id ?: 0,
        name = this.name ?: "",
    )
}