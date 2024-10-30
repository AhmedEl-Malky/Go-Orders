package com.example.go_orders.state

import com.example.go_orders.data.State
import com.example.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class RestaurantInfoUIState(
    val restaurant: State<List<RestaurantUIState>> = State.Loading,
    val menuItems:List<MenuItemUIState> = emptyList(),
    val menuCategories:List<MenuCategoryUIState> = emptyList()
){
    @Serializable
    data class MenuCategoryUIState(
        @SerialName("category")
        val category:String = "",
        var isSelected:Boolean = true
    )
    @Serializable
    data class MenuItemUIState(
        @SerialName("category")
        val category: List<String?>?,
        @SerialName("created_at")
        val createdAt: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("id")
        val id: Int?,
        @SerialName("image")
        val image: String?,
        @SerialName("isAvilable")
        val isAvilable: Boolean?,
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
        @SerialName("variants")
        val variants: String?
    )
}
