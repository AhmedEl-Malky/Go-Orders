package com.example.goorders.home.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDTO(
    @SerialName("active")
    val active: Boolean?,
    @SerialName("address")
    val address: String?,
    @SerialName("categories_list")
    val categories: List<String>?,
    @SerialName("city_id")
    val cityId: Int?,
    @SerialName("close_time")
    val closeTime: String?,
    @SerialName("cover")
    val cover: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_active")
    val isActive: Boolean?,
    @SerialName("logo")
    val logo: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("online_orders")
    val onlineOrders: String?,
    @SerialName("open_now")
    val openNow: Boolean?,
    @SerialName("open_time")
    val openTime: String?,
    @SerialName("other_info")
    val otherInfo: String?,
    @SerialName("owner")
    val owner: String?,
    @SerialName("phone_number")
    val phoneNumber: String?,
    @SerialName("slug")
    val slug: String?
)