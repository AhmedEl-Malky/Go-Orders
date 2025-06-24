package com.example.goorders.app.di

import com.example.goorders.mainscreen.data.remote.CitiesService
import com.example.goorders.mainscreen.data.remote.CitiesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    fun provideCitiesService(client: HttpClient) : CitiesService = CitiesServiceImpl(client = client)
}