package com.example.go_orders.state

import com.example.go_orders.data.State
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class ExploreRestaurantsScreenUIState(
    val categories: List<CategoryUIState> = emptyList(),
    val selectedCategory:CategoryUIState = CategoryUIState(),
    val city: String = "",
    val searchInput: String = "",
    val isOpenFilter: Boolean = false,
    val restaurants: State<List<RestaurantUIState>> = State.Loading,
    val screenState:State<List<RestaurantUIState>> = State.Loading,
    val banners:List<BannerUIState> = listOf(
        BannerUIState(
            img = "https://iwpwngjhxeevmqgaohyk.supabase.co/storage/v1/object/public/banners/6645.jpg",
            title = "لأصحاب المحلات والمطاعم",
            subtitle = "كن شريكا مع ",
            highlight = "Go Orders",
            buttonText = "تواصل الآن"
        ),
        BannerUIState(
            img = "https://iwpwngjhxeevmqgaohyk.supabase.co/storage/v1/object/public/banners/city-banner-bg.jpg",
            title = "اطلب من Go Orders",
            subtitle = "مطاعم مدينة ",
            highlight = "منوف",
            buttonText = "تصفح العروض والخصومات"
        )
    )
) {

    @Serializable
    data class CategoryUIState(
        val id: Int = 0,
        @SerialName("created_at")
        val createdAt: String = "",
        val name: String = "",
        val slug: String = "",
        val icon: String = "",
        val order: Int = 0,
        var isSelected: Boolean = name == "الكل",
    )

    @Serializable
    data class RestaurantUIState(
        val id: Int = 0,
        val createdAt: String = "",
        val city: Int = 0,
        val name: String = "",
        val description: String = "",
        val address: String = "",
        @SerialName("phone_number")
        val phoneNumber: String = "",
        @SerialName("open_time")
        val openTime: String = "",
        @SerialName("close_time")
        val closeTime: String = "",
        @SerialName("other_info")
        val otherInfo: String? = null,
        val cover: String = "",
        val logo: String = "",
        @SerialName("open_now")
        val openNow: Boolean = false,
        val slug: String = "",
        @SerialName("online_orders")
        val onlineOrders: String = "",
        @SerialName("is_active")
        val isActive: Boolean = false,
        val active: Boolean = false,
        val categories: String = "",
        val owner: String? = null
    )

    data class BannerUIState(
        val img:String,
        val title : String,
        val subtitle:String,
        val highlight:String,
        val buttonText:String
    )
}