package com.example.goorders.restaurant.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemDTO(
    @SerialName("category")
    val category: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("isAvilable")
    val isAvailable: Boolean?,
    @SerialName("name")
    val name: String?,
    @SerialName("regPrice")
    val regPrice: Double?,
    @SerialName("restaurantId")
    val restaurantId: Int?,
    @SerialName("sellingPrice")
    val sellingPrice: Double?,
    @SerialName("variantType")
    val variantType: String?,
    @SerialName("variants_list")
    val variants: List<VariantDTO>?
)