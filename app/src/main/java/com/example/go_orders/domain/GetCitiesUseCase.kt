package com.example.go_orders.domain

import com.example.go_orders.data.Supabase
import com.example.go_orders.state.HomeScreenUIState.CityUIState

class GetCitiesUseCase {
    suspend operator fun invoke():List<CityUIState>{
        return Supabase.getCities().sortedBy { it.id }
    }
}