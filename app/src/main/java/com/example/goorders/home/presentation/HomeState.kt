package com.example.goorders.home.presentation

import com.example.goorders.core.domain.UiText
import com.example.goorders.home.domain.Banner
import com.example.goorders.home.domain.Category
import com.example.goorders.home.domain.Restaurant

data class HomeState(
    val isLoading: Boolean = true,
    val isError: UiText? = null,
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val searchQuery: String = "",
    val isOpenFilter: Boolean = false,
    val restaurants: List<Restaurant> = emptyList(),
    val filteredRestaurants: List<Restaurant> = emptyList(),
    val banners:List<Banner> = listOf(
        Banner(
            img = "https://iwpwngjhxeevmqgaohyk.supabase.co/storage/v1/object/public/banners/6645.jpg",
            title = "لأصحاب المحلات والمطاعم",
            subtitle = "كن شريكا مع ",
            highlight = "Go Orders",
            buttonText = "تواصل الآن"
        ),
        Banner(
            img = "https://iwpwngjhxeevmqgaohyk.supabase.co/storage/v1/object/public/banners/city-banner-bg.jpg",
            title = "اطلب من Go Orders",
            subtitle = "مطاعم مدينة ",
            highlight = "منوف",
            buttonText = "تصفح العروض والخصومات"
        )
    )
)