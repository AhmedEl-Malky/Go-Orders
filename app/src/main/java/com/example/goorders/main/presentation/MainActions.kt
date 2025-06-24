package com.example.goorders.main.presentation

import com.example.goorders.main.domain.City

sealed interface MainActions {
    data object OnCityFormToggle : MainActions
    data class OnCitySelect(val city: City) : MainActions
    data object OnCitiesMenuToggle : MainActions
    data object GetAllCities : MainActions
}