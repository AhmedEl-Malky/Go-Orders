package com.example.goorders.restaurant.data.remote


import com.example.goorders.BuildConfig
import com.example.goorders.core.data.remote.safeCall
import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.home.data.dto.RestaurantDTO
import com.example.goorders.restaurant.data.dto.MenuCategoryDTO
import com.example.goorders.restaurant.data.dto.MenuImageDTO
import com.example.goorders.restaurant.data.dto.MenuItemDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path

class RestaurantServiceImpl(
    private val client: HttpClient
) : RestaurantService {
    override suspend fun getRestaurantInfo(restaurantId: Int): Result<List<RestaurantDTO>, RemoteError> {
        return safeCall<List<RestaurantDTO>> {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path(
                        "restaurants"
                    )
                    parameters{
                        parameter("id", "eq.$restaurantId")
                        parameter("select", "*")
                    }
                }
            }
        }
    }

    override suspend fun getMenuCategories(restaurantId: Int): Result<List<MenuCategoryDTO>, RemoteError> {
        return safeCall<List<MenuCategoryDTO>> {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path(
                        "menuItems"
                    )
                    parameters {
                        parameter("restaurantId", "eq.$restaurantId")
                        parameter("select", "category")
                    }
                }
            }
        }
    }

    override suspend fun getMenuItems(restaurantId: Int): Result<List<MenuItemDTO>, RemoteError> {
        return safeCall<List<MenuItemDTO>> {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path(
                        "menuItems"
                    )
                    parameters {
                        parameter("restaurantId", "eq.$restaurantId")
                        parameter("select", "*")
                    }
                }
            }
        }
    }

    override suspend fun getMenuImages(restaurantId: Int): Result<List<MenuImageDTO>, RemoteError> {
        return safeCall<List<MenuImageDTO>> {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.SUPABASE_URL
                    path("menusImages")
                    parameters {
                        append("restaurantId", "eq.$restaurantId")
                        append("select", "*")
                    }
                }
            }
        }
    }

}