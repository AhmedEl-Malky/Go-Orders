package com.example.goorders.restaurant.data.mappers

import com.example.goorders.restaurant.data.dto.MenuCategoryDTO
import com.example.goorders.restaurant.data.dto.MenuImageDTO
import com.example.goorders.restaurant.data.dto.MenuItemDTO
import com.example.goorders.restaurant.domain.MenuImage
import com.example.goorders.restaurant.domain.MenuItem

fun MenuItemDTO.toMenuItem() = MenuItem(
    id = this.id ?: 0,
    name = this.name ?: "",
    image = image,
    regPrice = this.regPrice ?: 0.0,
    sellingPrice = this.sellingPrice ?: 0.0,
    description = this.description ?: "",
    isAvailable = this.isAvailable == true,
    category = category ?: "",
    variants = variants?.map { it.toVariant() } ?: emptyList()
)

fun MenuCategoryDTO.toMenuCategory() : String {
    return this.category ?: ""
}


fun MenuImageDTO.toMenuImage() = MenuImage(
    id = this.id ?: 0,
    restaurantId = this.restaurantId ?: 0,
    url = this.url ?: ""
)