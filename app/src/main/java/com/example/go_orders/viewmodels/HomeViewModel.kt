package com.example.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.go_orders.domain.GetCitiesUseCase
import com.example.go_orders.state.HomeScreenUIState
import com.example.go_orders.state.HomeScreenUIState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _state = MutableStateFlow(HomeScreenUIState())
    val state:StateFlow<HomeScreenUIState> = _state

    private val getCitiesUseCase = GetCitiesUseCase()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(availableCities = getCitiesUseCase()) }
            _state.update { it.copy(city = it.availableCities.first()) }
        }
    }

    fun onSelectCity(city: CityUIState){
        _state.update { it.copy(city = city) }
    }

    fun showCityForm(){
        _state.update { it.copy(isCityFormShown = true) }
    }

    fun dismissCityForm(){
        _state.update { it.copy(isCityFormShown = false) }
    }

    fun expandCitiesMenu(){
        _state.update { it.copy(isCitiesMenuExpanded = true) }
    }

    fun collapseCitiesMenu(){
        _state.update { it.copy(isCitiesMenuExpanded = false) }
    }
}