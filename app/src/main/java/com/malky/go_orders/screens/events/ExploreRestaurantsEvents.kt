package com.malky.go_orders.screens.events

import com.malky.go_orders.state.ExploreRestaurantsUIState.CategoryUIState

sealed interface ExploreRestaurantsEvents {
    data class FilterOpenedRestaurants(val isOpen:Boolean):ExploreRestaurantsEvents
    data class SearchForRestaurant(val searchInput:String):ExploreRestaurantsEvents
    data class OnSelectCategory(val selectedCategory: CategoryUIState):ExploreRestaurantsEvents
}