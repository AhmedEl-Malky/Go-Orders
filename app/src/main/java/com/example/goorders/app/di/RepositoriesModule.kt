package com.example.goorders.app.di

import com.example.goorders.home.data.remote.HomeServices
import com.example.goorders.home.data.repository.HomeRepositoryImpl
import com.example.goorders.home.domain.HomeRepository
import com.example.goorders.main.data.remote.MainService
import com.example.goorders.main.data.repository.MainRepositoryImpl
import com.example.goorders.main.domain.MainRepository
import com.example.goorders.restaurant.data.remote.RestaurantService
import com.example.goorders.restaurant.data.repository.RestaurantRepositoryImpl
import com.example.goorders.restaurant.domain.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideMainRepository(service: MainService): MainRepository = MainRepositoryImpl(service = service)

    @Provides
    @Singleton
    fun provideHomeRepository(service: HomeServices): HomeRepository = HomeRepositoryImpl(service = service)

    @Provides
    @Singleton
    fun provideRestaurantRepository(service: RestaurantService): RestaurantRepository = RestaurantRepositoryImpl(service = service)

}