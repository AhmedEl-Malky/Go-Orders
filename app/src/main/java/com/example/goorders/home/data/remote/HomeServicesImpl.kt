package com.example.goorders.home.data.remote

import com.example.goorders.BuildConfig
import com.example.goorders.core.data.remote.safeCall
import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.home.data.dto.CategoryDTO
import com.example.goorders.home.data.dto.RestaurantDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path

class HomeServicesImpl(
    private val client: HttpClient
) : HomeServices {
    override suspend fun getRestaurants(cityID: Int): Result<List<RestaurantDTO>, RemoteError> {
        return safeCall<List<RestaurantDTO>> {
            client.get {
                url{
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path(
                        "restaurants",
                    )
                    parameter("city_id","eq.$cityID")
                    parameter("select","*")
                }
            }
        }
    }

    override suspend fun getCategories(): Result<List<CategoryDTO>, RemoteError> {
        return safeCall<List<CategoryDTO>> {
            client.get {
                url{
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path(
                        "categories",
                    )
                    parameter("select","*")
                }
            }
        }
    }
}