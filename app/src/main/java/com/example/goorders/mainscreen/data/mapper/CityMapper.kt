package com.example.goorders.mainscreen.data.mapper

import com.example.goorders.mainscreen.data.dto.CityDTO
import com.example.goorders.mainscreen.domain.City

fun CityDTO.toCity(): City{
    return City(
        id = this.id ?: 0,
        name = this.name ?: "",
    )
}