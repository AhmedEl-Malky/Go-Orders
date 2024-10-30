package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.data.State
import com.example.go_orders.state.HomeUIState.CityUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitiesUseCase @Inject constructor(
    private val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke():Flow<State<List<CityUIState>>> {
        return StateHandler { supabaseClient.getCities().sortedBy { it.id } }
    }
}