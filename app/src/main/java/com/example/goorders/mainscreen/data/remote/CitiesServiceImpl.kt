package com.example.goorders.mainscreen.data.remote

import com.example.goorders.BuildConfig
import com.example.goorders.core.data.remote.safeCall
import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.mainscreen.data.dto.CityDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path

class CitiesServiceImpl(
    private val client: HttpClient
) : CitiesService {
    override suspend fun getCities(): Result<List<CityDTO>, RemoteError> {
        return safeCall<List<CityDTO>> {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path("cities")
                    parameters {
                        append(name = "select", value = "*")
                    }
                }
            }
        }
    }
}