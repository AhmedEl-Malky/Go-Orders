package com.example.goorders.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goorders.core.data.local.DataStorePref
import com.example.goorders.core.domain.onError
import com.example.goorders.core.domain.onSuccess
import com.example.goorders.core.presentation.toUiText
import com.example.goorders.main.domain.MainRepository
import com.example.goorders.main.domain.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val citiesRepo: MainRepository,
    private val dataStore: DataStorePref
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        getAllCities()
        getCurrentCity()
    }
    fun onAction(action: MainActions) {
        when (action) {
            MainActions.OnCitiesMenuToggle -> onCitiesMenuToggle()
            is MainActions.OnCitySelect -> onCitySelect(action.city)
            MainActions.OnCityFormToggle -> onCityFormToggle()
            MainActions.GetAllCities -> getAllCities()
        }
    }

    private fun onCitiesMenuToggle() {
        _state.update {
            it.copy(
                isCitiesMenuExpanded = !it.isCitiesMenuExpanded
            )
        }
    }

    private fun getAllCities() {
        viewModelScope.launch {
            citiesRepo.getCities()
                .onSuccess { cities ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            availableCities = cities
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            availableCities = emptyList(),
                            isError = error.toUiText()
                        )
                    }
                }
        }
    }

    private fun onCitySelect(city: City) {
        _state.update {
            it.copy(
                currentCity = city
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.changeCity(city)
        }
    }

    private fun getCurrentCity() {
        viewModelScope.launch(Dispatchers.IO) {
            val city = City(
                id = dataStore.getCityID() ?: 0,
                name = dataStore.getCityName() ?: "",
            )
            _state.update {
                it.copy(
                    currentCity = city
                )
            }
        }
    }

    private fun onCityFormToggle() {
        _state.update {
            it.copy(
                isCityFormVisible = !it.isCityFormVisible,
            )
        }
    }
}