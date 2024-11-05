package com.malky.go_orders.screens.events

import com.malky.go_orders.state.HomeUIState.CityUIState

sealed interface HomeEvents {
    data object ShowCityForm:HomeEvents
    data object DismissCityForm:HomeEvents
    data class OnSelectCity(val city: CityUIState):HomeEvents
    data object ExpandCitiesMenu:HomeEvents
    data object CollapseCitiesMenu:HomeEvents
}