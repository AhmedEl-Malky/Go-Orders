package com.example.goorders.mainscreen.presentation

import com.example.goorders.mainscreen.domain.City

sealed interface MainActions {
    data object OnCityFormToggle : MainActions
    data class OnCitySelect(val city: City) : MainActions
    data object OnCitiesMenuToggle : MainActions
    data object GetAllCities : MainActions
}