package com.example.go_orders.di

import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.domain.AllRestaurantsUseCase
import com.example.go_orders.domain.CategoriesUseCase
import com.example.go_orders.domain.CitiesUseCase
import com.example.go_orders.domain.MenuCategoriesUseCase
import com.example.go_orders.domain.MenuItemsUseCase
import com.example.go_orders.domain.RestaurantUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideRestaurantsUseCase(supabaseClient: Supabase): AllRestaurantsUseCase = AllRestaurantsUseCase(supabaseClient)

    @Provides
    fun provideCategoriesUseCase(supabaseClient: Supabase): CategoriesUseCase = CategoriesUseCase(supabaseClient)

    @Provides
    fun provideCitiesUseCase(supabaseClient: Supabase): CitiesUseCase = CitiesUseCase(supabaseClient)

    @Provides
    fun provideRestaurantUseCase(supabaseClient: Supabase): RestaurantUseCase = RestaurantUseCase(supabaseClient)

    @Provides
    fun provideMenuUseCase(supabaseClient: Supabase): MenuCategoriesUseCase = MenuCategoriesUseCase(supabaseClient)

    @Provides
    fun provideMenuItemsUseCase(supabaseClient: Supabase): MenuItemsUseCase = MenuItemsUseCase(supabaseClient)

}