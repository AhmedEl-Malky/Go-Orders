package com.malky.go_orders.state

import com.malky.go_orders.data.State
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class HomeUIState(
    val availableCities:State<List<CityUIState>> = State.Loading,
    val city: CityUIState = CityUIState(),
    val isCityFormShown: Boolean = false,
    val isCitiesMenuExpanded:Boolean = false
) {
    @Serializable
    data class CityUIState(
        val id: Int = 0,
        @SerialName("created_at") val createdAt: String = "",
        val name: String = "",
        val slug: String = ""
    )
}