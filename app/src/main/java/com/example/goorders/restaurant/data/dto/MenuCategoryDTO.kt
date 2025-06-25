package com.example.goorders.restaurant.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuCategoryDTO(
    @SerialName("categories")
    val categories: List<String>?
)