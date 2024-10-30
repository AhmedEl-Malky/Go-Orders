package com.example.go_orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.go_orders.data.local.DataStore
import com.example.go_orders.domain.CitiesUseCase
import com.example.go_orders.state.HomeUIState
import com.example.go_orders.state.HomeUIState.CityUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase,
    private val dataStore: DataStore
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            citiesUseCase().collect { result ->
                _state.update { it.copy(availableCities = result) }
            }
            dataStore.setInitialCity(_state.value.availableCities.toData()?.first() ?: CityUIState())
            getCurrentCity()
        }
    }

    fun onSelectCity(city: CityUIState) {
        _state.update { it.copy(city = city) }
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.changeCity(city)
        }
    }

    private fun getCurrentCity() {
        viewModelScope.launch(Dispatchers.IO) {
            val city = CityUIState(
                id = dataStore.getCityID() ?: 0,
                name = dataStore.getCityName() ?: "",
                slug = dataStore.getCitySlug() ?: "",
                createdAt = dataStore.getCityCreate() ?: ""
            )
            _state.update { it.copy(city = city) }
        }
    }

    fun showCityForm() {
        _state.update { it.copy(isCityFormShown = true) }
    }

    fun dismissCityForm() {
        _state.update { it.copy(isCityFormShown = false) }
    }

    fun expandCitiesMenu() {
        _state.update { it.copy(isCitiesMenuExpanded = true) }
    }

    fun collapseCitiesMenu() {
        _state.update { it.copy(isCitiesMenuExpanded = false) }
    }
}