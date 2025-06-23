package com.example.goorders.mainscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goorders.mainscreen.domain.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val citiesUseCase: CitiesUseCase,
//    private val dataStore: DataStore
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            citiesUseCase().collect { result ->
//                _state.update { it.copy(availableCities = result) }
//            }
//            dataStore.setInitialCity(_state.value.availableCities.toData()?.first() ?: CityUIState())
//            getCurrentCity()
//        }
//    }

    fun onAction(action: MainAction) {
        when (action) {
            MainAction.OnCitiesMenuToggle -> TODO()
            is MainAction.OnCitySelect -> onCitySelect(action.city)
            MainAction.OnCityFormToggle -> onCityFormToggle()
        }
    }

    private fun onCitySelect(city: City) {
        _state.update {
            it.copy(
                currentCity = city
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
//            dataStore.changeCity(city)
        }
    }

    private fun getCurrentCity() {
        viewModelScope.launch(Dispatchers.IO) {
//            val city = City(
//                id = dataStore.getCityID() ?: 0,
//                name = dataStore.getCityName() ?: "",
//            )
//            _state.update {
//                it.copy(
//                    currentCity = city
//                )
//            }
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