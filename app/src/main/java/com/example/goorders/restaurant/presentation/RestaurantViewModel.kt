package com.example.goorders.restaurant.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goorders.core.domain.onError
import com.example.goorders.core.domain.onSuccess
import com.example.goorders.core.presentation.toUiText
import com.example.goorders.restaurant.domain.MenuOptions
import com.example.goorders.restaurant.domain.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repo: RestaurantRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(RestaurantState())
    val state = _state.asStateFlow()

    fun onAction(action: RestaurantActions) {
        when (action) {
            is RestaurantActions.GetMenuCategories -> getMenuCategories(action.restaurantID)
            is RestaurantActions.GetMenuImages -> getMenuImages(action.restaurantID)
            is RestaurantActions.GetMenuItems -> getMenuItems(action.restaurantID)
            is RestaurantActions.GetRestaurantInfo -> getRestaurantInfo(action.restaurantID)
            is RestaurantActions.OnOptionSelect -> onOptionSelect(action.option)
        }
    }

    private fun getRestaurantInfo(id: Int) {
        viewModelScope.launch {
            repo.getRestaurantInfo(id)
                .onSuccess { restaurant ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = null,
                            restaurant = restaurant
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = error.toUiText(),
                            restaurant = null
                        )
                    }
                }
        }
    }

    private fun getMenuCategories(id: Int) {
        viewModelScope.launch {
            repo.getMenuCategories(id)
                .onSuccess { categories ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = null,
                            menuCategories = categories,
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = error.toUiText(),
                            menuCategories = emptyList()
                        )
                    }
                }
        }
    }


    private fun getMenuItems(id: Int) {
        viewModelScope.launch {
            repo.getMenuItems(id)
                .onSuccess { items ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = null,
                            menuItems = items
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = error.toUiText(),
                            menuItems = emptyList()
                        )
                    }
                }
        }
    }

    private fun onOptionSelect(option: MenuOptions) {
        _state.update {
            it.copy(menuOption = option)
        }
    }

    private fun getMenuImages(id: Int) {
        viewModelScope.launch {
            repo.getMenuImages(id)
                .onSuccess { images ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = null,
                            menuImages = images
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = error.toUiText(),
                            menuImages = emptyList()
                        )
                    }
                }
        }
    }

}