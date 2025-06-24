package com.example.goorders.home.data.mappers

import com.example.goorders.home.data.dto.CategoryDTO
import com.example.goorders.home.domain.Category

fun CategoryDTO.toCategory() = Category(
    id = this.id ?: 0,
    name = this.name ?: "",
    icon = this.icon ?: "",
    order = this.order ?: 0,
    slug = this.slug ?: "",
)