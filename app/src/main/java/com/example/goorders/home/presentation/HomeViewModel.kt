package com.example.goorders.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goorders.core.domain.onError
import com.example.goorders.core.domain.onSuccess
import com.example.goorders.core.presentation.toUiText
import com.example.goorders.home.domain.Category
import com.example.goorders.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state = _state.asStateFlow()

    fun onAction(action: HomeActions) {
        when (action) {
            is HomeActions.OnCategorySelect -> onCategorySelect(action.category)
            HomeActions.OnOpenRestaurantsFilter -> onOpenRestaurantsFilter()
            is HomeActions.OnRestaurantsSearchChange -> onRestaurantsSearchChange(action.query)
            HomeActions.GetCategories -> getCategories()
            is HomeActions.GetRestaurants -> getAllRestaurants(action.cityID)
        }
    }

    private fun onOpenRestaurantsFilter() {
        _state.update {
            it.copy(
                isOpenFilter = !it.isOpenFilter
            )
        }
        filterRestaurants()
    }

    private fun getCategories() {
        viewModelScope.launch {
            repo.getCategories()
                .onSuccess { categories ->
                    _state.update {
                        it.copy(
                            categories = categories,
                            selectedCategory = categories.first(),
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isError = error.toUiText()
                        )
                    }
                }
        }
    }

    private fun getAllRestaurants(cityID: Int) {
        viewModelScope.launch {
            repo.getRestaurants(cityID)
                .onSuccess { restaurants ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = null,
                            restaurants = restaurants,
                            filteredRestaurants = restaurants
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = error.toUiText(),
                            restaurants = emptyList(),
                            filteredRestaurants = emptyList()
                        )
                    }
                }
        }
    }

    private fun onRestaurantsSearchChange(query: String) {
        _state.update { state ->
            state.copy(
                searchQuery = query,
            )
        }
        filterRestaurants()
    }

    fun onCategorySelect(category: Category) {
        _state.update {
            it.copy(selectedCategory = category)
        }
        filterRestaurants()
    }

    private fun filterRestaurants() {
        _state.update {
            it.copy(
                filteredRestaurants = it.restaurants
                    .filter { it.name.contains(_state.value.searchQuery) }
                    .filter {
                        if (_state.value.selectedCategory.slug != "all") it.categories.contains(
                            _state.value.selectedCategory.slug
                        ) else true
                    }
                    .filter { if (_state.value.isOpenFilter == true) it.isOpen else true }
            )
        }
    }
}
