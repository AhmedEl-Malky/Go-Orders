package com.example.goorders.core.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.goorders.BuildConfig
import com.example.goorders.main.domain.City
import kotlinx.coroutines.flow.first

class DataStorePref(
    private val context: Context
) {
    private val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(
        name = DATASTORE_NAME
    )


    suspend fun changeCity(city: City) {
        context.dataStore.edit { DATASTORE_NAME ->
            DATASTORE_NAME[CITY_NAME_KEY] = city.name
            DATASTORE_NAME[CITY_ID_KEY] = city.id
        }
    }

    suspend fun setInitialCity(city: City) {
        if (context.dataStore.data.first()[CITY_NAME_KEY] == null)
            context.dataStore.edit { DATASTORE_NAME ->
                DATASTORE_NAME[CITY_NAME_KEY] = city.name
                DATASTORE_NAME[CITY_ID_KEY] = city.id
            }

    }

    suspend fun getCityName(): String? {
        return context.dataStore.data.first()[CITY_NAME_KEY]
    }

    suspend fun getCityID(): Int? {
        return context.dataStore.data.first()[CITY_ID_KEY]
    }


    private companion object {
        const val DATASTORE_NAME = BuildConfig.DATASTORE_NAME
        const val CITY_NAME = "CITY_NAME"
        const val CITY_ID = "CITY_ID"
        val CITY_NAME_KEY = stringPreferencesKey(CITY_NAME)
        val CITY_ID_KEY = intPreferencesKey(CITY_ID)
    }
}