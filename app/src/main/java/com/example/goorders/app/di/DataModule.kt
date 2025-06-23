package com.example.goorders.app.di

import android.content.Context
import com.example.goorders.core.data.remote.Supabse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideSupabaseClient(): SupabaseClient = Supabse.create()

//    @Provides
//    fun provideDataStore(@ApplicationContext context: Context): DataStore = DataStore(context)

}