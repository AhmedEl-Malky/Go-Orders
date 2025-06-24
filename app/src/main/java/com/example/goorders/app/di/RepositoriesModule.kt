package com.example.goorders.app.di

import com.example.goorders.mainscreen.data.remote.CitiesService
import com.example.goorders.mainscreen.data.repository.CitiesRepositoryImpl
import com.example.goorders.mainscreen.domain.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

//    @Provides
//    fun provideRestaurantsUseCase(supabaseClient: Supabase): AllRestaurantsUseCase = AllRestaurantsUseCase(supabaseClient)

//    @Provides
//    fun provideCategoriesUseCase(supabaseClient: Supabase): CategoriesUseCase = CategoriesUseCase(supabaseClient)

    @Provides
    fun provideCitiesRepository(service: CitiesService): CitiesRepository = CitiesRepositoryImpl(service = service)

//    @Provides
//    fun provideRestaurantUseCase(supabaseClient: Supabase): RestaurantUseCase = RestaurantUseCase(supabaseClient)

//    @Provides
//    fun provideMenuUseCase(supabaseClient: Supabase): MenuCategoriesUseCase = MenuCategoriesUseCase(supabaseClient)

//    @Provides
//    fun provideMenuItemsUseCase(supabaseClient: Supabase): MenuItemsUseCase = MenuItemsUseCase(supabaseClient)

//    @Provides
//    fun provideMenuImagesUseCase(supabaseClient: Supabase): MenuImagesUseCase = MenuImagesUseCase(supabaseClient)

}