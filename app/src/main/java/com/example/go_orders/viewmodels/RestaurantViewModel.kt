package com.example.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.go_orders.domain.MenuCategoriesUseCase
import com.example.go_orders.domain.MenuItemsUseCase
import com.example.go_orders.domain.RestaurantUseCase
import com.example.go_orders.state.RestaurantInfoUIState
import com.example.go_orders.state.RestaurantInfoUIState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantUseCase: RestaurantUseCase,
    private val menuCategoriesUseCase: MenuCategoriesUseCase,
    private val menuItemsUseCase: MenuItemsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RestaurantInfoUIState())
    val state: StateFlow<RestaurantInfoUIState> = _state.asStateFlow()

    fun fetchRestaurantInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantUseCase(id).collect { result ->
                _state.update { it.copy(restaurant = result) }
            }
        }
    }

    fun fetchMenuCategories(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(menuCategories = menuCategoriesUseCase(id)) }
        }
    }

    fun fetchMenuItems(category: String): List<MenuItemUIState> {
        var result = emptyList<MenuItemUIState>()
        viewModelScope.async(Dispatchers.IO) {
            result = menuItemsUseCase(category)
        }
        return result
    }

}