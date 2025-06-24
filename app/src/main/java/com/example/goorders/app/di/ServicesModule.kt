package com.example.goorders.app.di

import com.example.goorders.home.data.remote.HomeServices
import com.example.goorders.home.data.remote.HomeServicesImpl
import com.example.goorders.main.data.remote.MainService
import com.example.goorders.main.data.remote.MainServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun provideMainService(client: HttpClient) : MainService = MainServiceImpl(client = client)

    @Provides
    @Singleton
    fun provideHomeServices(client: HttpClient) : HomeServices = HomeServicesImpl(client = client)
}