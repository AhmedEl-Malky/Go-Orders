package com.example.goorders.main.presentation

import com.example.goorders.core.domain.UiText
import com.example.goorders.main.domain.City

data class MainState(
    val isLoading: Boolean = true,
    val isError: UiText? = null,
    val availableCities:List<City> = emptyList(),
    val currentCity: City? = null,
    val isCityFormVisible: Boolean = false,
    val isCitiesMenuExpanded:Boolean = false
)