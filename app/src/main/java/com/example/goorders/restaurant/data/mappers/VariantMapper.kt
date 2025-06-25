package com.example.goorders.restaurant.data.mappers

import com.example.goorders.restaurant.data.dto.VariantDTO
import com.example.goorders.restaurant.domain.Variant

fun VariantDTO.toVariant() = Variant(
    price = this.price ?: 0.0,
    size = this.size ?: ""
)