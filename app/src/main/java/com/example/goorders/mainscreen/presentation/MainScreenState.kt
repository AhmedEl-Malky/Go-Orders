package com.example.goorders.mainscreen.presentation

import com.example.goorders.mainscreen.domain.City

data class MainScreenState(
    val availableCities:List<City> = emptyList(),
    val currentCity: City? = null,
    val isCityFormVisible: Boolean = false,
    val isCitiesMenuExpanded:Boolean = false
)