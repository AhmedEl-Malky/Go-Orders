package com.example.goorders.mainscreen.presentation

import com.example.goorders.mainscreen.domain.City

sealed interface MainAction {
    data object OnCityFormToggle : MainAction
    data class OnCitySelect(val city: City) : MainAction
    data object OnCitiesMenuToggle : MainAction
}