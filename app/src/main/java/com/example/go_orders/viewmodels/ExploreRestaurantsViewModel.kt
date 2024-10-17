package com.example.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.go_orders.domain.GetAllRestaurantsUseCase
import com.example.go_orders.domain.GetCategoriesUseCase
import com.example.go_orders.state.ExploreRestaurantsScreenUIState
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.CategoryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ExploreRestaurantsViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExploreRestaurantsScreenUIState())

    val state: StateFlow<ExploreRestaurantsScreenUIState> = _state

    private val getAllRestaurantsUseCase = GetAllRestaurantsUseCase()

    private val getCategoriesUseCase = GetCategoriesUseCase()

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase().collect { result ->
                _state.update { it.copy(categories = result) }
            }
        }
        _state.update {
            it.copy(
                selectedCategory = it.categories.toData()?.first() ?: CategoryUIState()
            )
        }
    }

    fun startScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(isOpen = _state.value.isOpenFilter).collect { result ->
                _state.update { it.copy(screenState = result) }
            }
        }
    }

    fun getAllRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(isOpen = _state.value.isOpenFilter).collect { result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

    fun filterOpenedRestaurants(isOpen: Boolean) {
        _state.update { it.copy(isOpenFilter = isOpen) }
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(
                isOpen = isOpen,
                searchInput = _state.value.searchInput
            ).collect { result ->
                _state.update { it.copy(restaurants = result) }
            }

        }
    }

    fun searchForRestaurant(searchInput: String) {
        _state.update { it.copy(searchInput = searchInput) }
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(
                searchInput = searchInput,
                isOpen = _state.value.isOpenFilter
            ).collect { result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }
}