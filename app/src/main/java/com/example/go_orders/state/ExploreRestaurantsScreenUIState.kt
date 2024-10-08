package com.example.go_orders.state

import com.example.go_orders.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class ExploreRestaurantsScreenUIState(
    val categories: List<Category> = listOf(
        Category(name = "الكل", img = R.drawable.all, isSelected = true),
        Category(name = "دجاج", img = R.drawable.chicken, isSelected = false),
        Category(name = "بيتزا", img = R.drawable.pizza, isSelected = false),
        Category(name = "برجر", img = R.drawable.burger, isSelected = false),
        Category(name = "كريب", img = R.drawable.crepe, isSelected = false),
        Category(name = "ساندويتش", img = R.drawable.sandwich, isSelected = false),
        Category(name = "شاورما", img = R.drawable.shawarma, isSelected = false),
        Category(name = "لحوم", img = R.drawable.meat, isSelected = false),
        Category(name = "كبده", img = R.drawable.liver, isSelected = false),
        Category(name = "مأكولات بحرية", img = R.drawable.spaghetti, isSelected = false),
        Category(name = "كشري", img = R.drawable.kabab, isSelected = false),
        Category(name = "فول وفلافل", img = R.drawable.falafel, isSelected = false),
        Category(name = "مخبوزات", img = R.drawable.backing, isSelected = false),
        Category(name = "سوشي", img = R.drawable.sushi, isSelected = false),
        Category(name = "حلويات", img = R.drawable.sweets, isSelected = false),
    ),
    val city: String = "",
    val searchInput: String = "",
    val isOpenFilter: Boolean = false,
    val restaurants: List<RestaurantUIState> = listOf()
) {

    data class Category(
        val name: String,
        val img: Int,
        val isSelected: Boolean,
    )
    @Serializable
    data class CategoryUIState(
        val id: Int = 0,
        @SerialName("created_at")
        val createdAt: Long = 0,
        val name: String = "",
        val slug: String = "",
        val icon: String = "",
        val order: Int = 0,
        val isSelected: Boolean = false,
    )

    @Serializable
    data class RestaurantUIState(
        val id: Int = 0,
        val createdAt: Long = 0,
        val city: Int = 0,
        val name: String = "",
        val description: String = "",
        val address: String = "",
        @SerialName("phone_number")
        val phoneNumber: String = "",
        @SerialName("open_time")
        val openTime: Long = 0,
        @SerialName("close_time")
        val closeTime: Long = 0,
        @SerialName("other_info")
        val otherInfo: String? = null,
        val cover: String = "",
        val logo: String = "",
        @SerialName("open_now")
        val openNow: Boolean = false,
        val slug: String = "",
        @SerialName("online_orders")
        val onlineOrders: Long = 0,
        @SerialName("is_active")
        val isActive: Boolean = false,
        val active: Boolean = false,
        val categories: String = "",
        val owner: String = ""
    )
}