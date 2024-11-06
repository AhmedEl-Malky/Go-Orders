package com.malky.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.go_orders.domain.MenuCategoriesUseCase
import com.malky.go_orders.domain.MenuImagesUseCase
import com.malky.go_orders.domain.MenuItemsUseCase
import com.malky.go_orders.domain.RestaurantUseCase
import com.malky.go_orders.screens.events.RestaurantEvents
import com.malky.go_orders.state.RestaurantInfoUIState
import com.malky.go_orders.state.RestaurantInfoUIState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val menuItemsUseCase: MenuItemsUseCase,
    private val menuImagesUseCase: MenuImagesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RestaurantInfoUIState())
    val state: StateFlow<RestaurantInfoUIState> = _state.asStateFlow()

    fun onEvent(event: RestaurantEvents){
        when(event){
            is RestaurantEvents.FetchRestaurantInfo -> fetchRestaurantInfo(event.restaurantID)
            is RestaurantEvents.FetchMenuCategories -> fetchMenuCategories(event.restaurantID)
            is RestaurantEvents.FetchMenuItems -> fetchMenuItems(event.restaurantID)
            is RestaurantEvents.FetchMenuImages -> fetchMenuImages(event.restaurantID)
            is RestaurantEvents.SelectOption -> selectOption(event.option)
        }
    }

    private fun fetchRestaurantInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantUseCase(id).collect { result ->
                _state.update { it.copy(restaurant = result) }
            }
        }
    }

    private fun fetchMenuCategories(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(menuCategories = menuCategoriesUseCase(id)) }
        }
    }

    private fun fetchMenuItems(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(menuItems = menuItemsUseCase(id)) }
        }
    }

    private fun selectOption(option: MenuOptions) {
        _state.update { it.copy(menuOption = option) }
    }

    private fun fetchMenuImages(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(menuImages = menuImagesUseCase(id)) }
        }
    }

}