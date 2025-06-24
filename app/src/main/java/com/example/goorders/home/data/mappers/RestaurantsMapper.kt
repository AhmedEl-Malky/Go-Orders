package com.example.goorders.home.data.mappers

import com.example.goorders.home.data.dto.RestaurantDTO
import com.example.goorders.home.domain.Restaurant

fun RestaurantDTO.toRestaurant() = Restaurant(
    id = this.id ?: 0,
    name = this.name ?: "",
    description = this.description ?: "",
    cover = this.cover ?: "",
    logo = this.logo ?: "",
    isOpen = this.openNow ?: false,
    categories = this.categories ?: emptyList()
)