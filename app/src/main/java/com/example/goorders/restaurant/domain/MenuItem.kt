package com.example.goorders.restaurant.domain

data class MenuItem(
    val id: Int,
    val name: String,
    val image: String? = null,
    val regPrice: Double,
    val sellingPrice: Double,
    val description: String,
    val isAvailable: Boolean,
    val category: String,
    val variants: List<Variant> = emptyList()
)