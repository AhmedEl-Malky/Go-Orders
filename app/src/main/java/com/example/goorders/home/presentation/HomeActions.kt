package com.example.goorders.home.presentation

import com.example.goorders.home.domain.Category


sealed interface HomeActions {
    data object OnOpenRestaurantsFilter:HomeActions
    data class OnRestaurantsSearchChange(val query:String):HomeActions
    data class OnCategorySelect(val category: Category):HomeActions
    data object GetCategories:HomeActions
    data class GetRestaurants(val cityID: Int): HomeActions
}