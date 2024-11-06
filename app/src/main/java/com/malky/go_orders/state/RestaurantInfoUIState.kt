package com.malky.go_orders.state

import com.malky.go_orders.data.State
import com.malky.go_orders.state.ExploreRestaurantsUIState.RestaurantUIState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class RestaurantInfoUIState(
    val restaurant: State<List<RestaurantUIState>> = State.Loading,
    val menuItems: List<MenuItemUIState> = emptyList(),
    val menuCategories: List<MenuCategoryUIState> = emptyList(),
    val menuOption:MenuOptions = MenuOptions.OnlineOrders,
    val menuImages:List<MenuImageUIState> = emptyList()
) {
    @Serializable
    data class MenuCategoryUIState(
        @SerialName("category")
        val name:String = "",
//        var isSelected:Boolean = false
    )

    @Serializable
    data class MenuItemUIState(
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
        @SerialName("variants")
        val variants: String?
    )

    @Serializable
    data class MenuImageUIState(
        @SerialName("created_at")
        val createdAt: String?,
        @SerialName("id")
        val id: Int?,
        @SerialName("restaurantId")
        val restaurantId: Int?,
        @SerialName("url")
        val url: String?
    )

    sealed class MenuOptions{
        data object OnlineOrders:MenuOptions()
        data object MenuImages:MenuOptions()
        data object RestaurantInfo:MenuOptions()
    }

}
