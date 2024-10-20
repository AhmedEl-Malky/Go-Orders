package com.example.go_orders.domain

import com.example.go_orders.data.Remote.GoOrdersServices
import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.data.State
import com.example.go_orders.state.HomeScreenUIState.CityUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val supabaseClient: GoOrdersServices
) {
    suspend operator fun invoke():Flow<State<List<CityUIState>>> {
        return StateHandler { supabaseClient.getCities().sortedBy { it.id } }
    }
}