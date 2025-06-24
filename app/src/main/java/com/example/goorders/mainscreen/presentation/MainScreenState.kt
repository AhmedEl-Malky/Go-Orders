package com.example.goorders.mainscreen.presentation

import com.example.goorders.core.domain.UiText
import com.example.goorders.mainscreen.domain.City

data class MainScreenState(
    val isLoading: Boolean = true,
    val isError: UiText? = null,
    val availableCities:List<City> = emptyList(),
    val currentCity: City? = null,
    val isCityFormVisible: Boolean = false,
    val isCitiesMenuExpanded:Boolean = false
)