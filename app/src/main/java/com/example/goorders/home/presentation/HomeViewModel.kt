package com.example.goorders.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goorders.core.domain.onError
import com.example.goorders.core.domain.onSuccess
import com.example.goorders.core.presentation.toUiText
import com.example.goorders.home.domain.Category
import com.example.goorders.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private fun getCategories(){
        viewModelScope.launch {
            repo.getCategories()
                .onSuccess { categories ->
                    _state.update {
                        it.copy(
                            categories = categories,
                            selectedCategory = categories.first()
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
    private fun getAllRestaurants(cityID: Int){
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
        _state.update {
            it.copy(
                searchQuery = query,
                filteredRestaurants = it.restaurants.toMutableList()
                    .apply { filter { it.name.contains(query) } }
            )
        }
    }

    private fun onOpenRestaurantsFilter() {
        _state.update {
            it.copy(
                isOpenFilter = !it.isOpenFilter,
                filteredRestaurants = if (it.isOpenFilter == true) it.restaurants.toMutableList()
                    .apply {
                        filter {
                            it.isOpen == true
                        }
                    } else it.restaurants
            )
        }
    }

    private fun filterRestaurantsByCategory(categorySlug: String) {
        _state.update {
            it.copy(
                filteredRestaurants = it.restaurants.toMutableList()
                    .apply { filter { it.categories.contains(categorySlug) } }
            )
        }
    }

    fun onCategorySelect(category: Category) {
        _state.update {
            it.copy(selectedCategory = category)
        }
        _state.update {
            it.copy(
                categories = it.categories.map { category ->
                    Category(
                        id = category.id,
                        name = category.name,
                        icon = category.icon,
                        order = category.order,
                        isSelected = category.name == it.selectedCategory!!.name,
                        slug = category.slug
                    )
                }
            )
        }
        filterRestaurantsByCategory(category.slug)
    }
}
