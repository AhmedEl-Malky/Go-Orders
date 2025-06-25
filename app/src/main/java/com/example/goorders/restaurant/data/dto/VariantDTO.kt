package com.example.goorders.restaurant.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VariantDTO(
    @SerialName("price")
    val price: Double?,
    @SerialName("size")
    val size: String?
)