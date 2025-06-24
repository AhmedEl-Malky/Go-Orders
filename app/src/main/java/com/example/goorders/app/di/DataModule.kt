package com.example.goorders.app.di

import android.content.Context
import com.example.goorders.core.data.local.DataStorePref
import com.example.goorders.core.data.remote.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideHttpClient(): HttpClient = HttpClientFactory.create()

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStorePref = DataStorePref(context)

}