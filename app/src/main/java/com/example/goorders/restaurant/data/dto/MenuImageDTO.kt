package com.example.goorders.restaurant.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuImageDTO(
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("restaurantId")
    val restaurantId: Int?,
    @SerialName("url")
    val url: String?
)