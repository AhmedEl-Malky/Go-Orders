package com.example.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.go_orders.domain.GetAllRestaurantsUseCase
import com.example.go_orders.domain.GetCategoriesUseCase
import com.example.go_orders.state.ExploreRestaurantsScreenUIState.*
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

    private val getAllRestaurantsUseCase = GetAllRestaurantsUseCase()

    private val getCategoriesUseCase = GetCategoriesUseCase()

    fun getCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase().collect{ result ->
                _state.update { it.copy(categories = result) }
            }
        }
        _state.update { it.copy(selectedCategory = it.categories.toData()?.first() ?: CategoryUIState()) }
    }

    fun getAllRestaurants(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(isOpen = _state.value.isOpenFilter).collect{ result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

    fun filterOpenedRestaurants(isOpen:Boolean){
        _state.update { it.copy(isOpenFilter = isOpen) }
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(_state.value.searchInput,isOpen).collect{ result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

    fun searchForRestaurant(searchInput:String){
        _state.update { it.copy(searchInput = searchInput) }
        viewModelScope.launch(Dispatchers.IO) {
            getAllRestaurantsUseCase(searchInput,_state.value.isOpenFilter).collect{ result ->
                _state.update { it.copy(restaurants = result) }
            }
        }
    }

}