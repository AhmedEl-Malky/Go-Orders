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
            _state.update { it.copy(categories = getCategoriesUseCase()) }
            _state.update { it.copy(selectedCategory = it.categories.first()) }
        }
    }

    fun startScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(
                isOpen = _state.value.isOpenFilter,
                searchInput = _state.value.searchInput,
                category = _state.value.selectedCategory.slug
            ).collect { result ->
                _state.update { it.copy(screenState = result) }
            }
        }
    }

    fun getAllRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(
                isOpen = _state.value.isOpenFilter,
                category = _state.value.selectedCategory.slug,
            ).collect { result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

    fun filterOpenedRestaurants(isOpen: Boolean) {
        _state.update { it.copy(isOpenFilter = isOpen) }
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(
                isOpen = isOpen,
                searchInput = _state.value.searchInput,
                category = _state.value.selectedCategory.slug
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
                isOpen = _state.value.isOpenFilter,
                category = _state.value.selectedCategory.slug
            ).collect { result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

    private fun categorizeRestaurants(selectedCategory: CategoryUIState) {
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(
                searchInput = _state.value.searchInput,
                isOpen = _state.value.isOpenFilter,
                category = selectedCategory.slug
            ).collect { result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

    fun onSelectCategory(selectedCategory: CategoryUIState) {
        _state.update { it.copy(selectedCategory = selectedCategory) }
        _state.update {
            it.copy(
                categories = it.categories.map { category ->
                    CategoryUIState(
                        id = category.id,
                        createdAt = category.createdAt,
                        name = category.name,
                        slug = category.slug,
                        icon = category.icon,
                        order = category.order,
                        isSelected = category.name == selectedCategory.name
                    )
                }
            )
        }
        categorizeRestaurants(selectedCategory)
    }
}
