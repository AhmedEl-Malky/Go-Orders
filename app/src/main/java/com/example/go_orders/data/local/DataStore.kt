package com.example.go_orders.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.go_orders.state.HomeUIState.CityUIState
import kotlinx.coroutines.flow.first

class DataStore(
    val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)


    suspend fun changeCity(city:CityUIState){
        context.dataStore.edit { DATASTORE_NAME ->
            DATASTORE_NAME[CITY_NAME_KEY] = city.name
            DATASTORE_NAME[CITY_ID_KEY] = city.id
            DATASTORE_NAME[CITY_SLUG_KEY] = city.slug
            DATASTORE_NAME[CITY_CREATE_KEY] = city.createdAt
        }
    }

    suspend fun setInitialCity(city:CityUIState){
        if(context.dataStore.data.first()[CITY_NAME_KEY] == null)
            context.dataStore.edit { DATASTORE_NAME ->
                DATASTORE_NAME[CITY_NAME_KEY] = city.name
                DATASTORE_NAME[CITY_ID_KEY] = city.id
            }

    }

    suspend fun getCityName():String?{
        return context.dataStore.data.first()[CITY_NAME_KEY]
    }
    suspend fun getCityID():Int?{
        return context.dataStore.data.first()[CITY_ID_KEY]
    }
    suspend fun getCitySlug():String?{
        return context.dataStore.data.first()[CITY_SLUG_KEY]
    }
    suspend fun getCityCreate():String?{
        return context.dataStore.data.first()[CITY_CREATE_KEY]
    }



    private companion object{
        const val DATASTORE_NAME = "GO_ORDERS"
        const val CITY_NAME = "CITY_NAME"
        const val CITY_SLUG = "CITY_SLUG"
        const val CITY_CREATE = "CITY_CREATED"
        const val CITY_ID = "CITY_ID"
        val CITY_NAME_KEY = stringPreferencesKey(CITY_NAME)
        val CITY_SLUG_KEY = stringPreferencesKey(CITY_SLUG)
        val CITY_CREATE_KEY = stringPreferencesKey(CITY_CREATE)
        val CITY_ID_KEY = intPreferencesKey(CITY_ID)
    }
}