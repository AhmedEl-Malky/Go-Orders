package com.example.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.go_orders.domain.GetAllRestaurantsUseCase
import com.example.go_orders.domain.GetCategoriesUseCase
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ExploreRestaurantsViewModel:ViewModel() {
    private val _state = MutableStateFlow(ExploreRestaurantsScreenUIState())
    val state:StateFlow<ExploreRestaurantsScreenUIState> = _state
    val getAllRestaurantsUseCase = GetAllRestaurantsUseCase()
    val getCategoriesUseCase = GetCategoriesUseCase()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(categories = getCategoriesUseCase()) }
        }
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(restaurants = getAllRestaurantsUseCase(isOpen = _state.value.isOpenFilter)) }
        }
    }

    fun filterOpenedRestaurants(isOpen:Boolean){
        _state.update { it.copy(isOpenFilter = isOpen) }
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(restaurants = getAllRestaurantsUseCase(_state.value.searchInput,isOpen)) }
        }
    }

    fun searchForRestaurant(searchInput:String){
        _state.update { it.copy(searchInput = searchInput) }
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(restaurants = getAllRestaurantsUseCase(searchInput,_state.value.isOpenFilter)) }
        }
    }

}