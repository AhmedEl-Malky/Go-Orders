package com.example.goorders.restaurant.presentation

import com.example.goorders.core.domain.UiText
import com.example.goorders.home.domain.Restaurant
import com.example.goorders.restaurant.domain.MenuImage
import com.example.goorders.restaurant.domain.MenuItem
import com.example.goorders.restaurant.domain.MenuOptions

data class RestaurantState(
    val isLoading: Boolean = true,
    val isError: UiText? = null,
    val restaurant: Restaurant? = null,
    val menuItems: List<MenuItem> = emptyList(),
    val menuCategories: List<String> = emptyList(),
    val menuOption: MenuOptions = MenuOptions.ONLINE_ORDERS,
    val menuImages: List<MenuImage> = emptyList()
)