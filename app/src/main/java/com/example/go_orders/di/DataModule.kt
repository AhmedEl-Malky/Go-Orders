package com.example.go_orders.di

import android.content.Context
import com.example.go_orders.data.Remote.Supabase
import com.example.go_orders.data.local.DataStore
import com.example.go_orders.domain.GetAllRestaurantsUseCase
import com.example.go_orders.domain.GetCategoriesUseCase
import com.example.go_orders.domain.GetCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideSupabaseClient(): Supabase = Supabase()

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore = DataStore(context)

}