package com.example.go_orders.domain

import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.data.State
import com.example.go_orders.state.HomeScreenUIState.CityUIState
import com.example.go_orders.utils.StateHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCitiesUseCase {
    suspend operator fun invoke():Flow<State<List<CityUIState>>> {
        return StateHandler { Supabase.getCities().sortedBy { it.id } }
    }
}