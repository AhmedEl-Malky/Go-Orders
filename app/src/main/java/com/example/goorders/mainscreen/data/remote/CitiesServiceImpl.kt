package com.example.goorders.mainscreen.data.remote

import com.example.goorders.core.data.remote.safeCall
import com.example.goorders.core.domain.RemoteError
import com.example.goorders.core.domain.Result
import com.example.goorders.mainscreen.data.dto.CityDTO
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import okio.IOException
import kotlin.coroutines.coroutineContext

class CitiesServiceImpl(
    private val client: SupabaseClient
) : CitiesService {
    override suspend fun getCities(): Result<List<CityDTO>, RemoteError> {
        val response = try {
            client.from("cities").select().decodeList<CityDTO>()
        } catch (e: SocketTimeoutException) {
            return Result.Error(RemoteError.REQUEST_TIMEOUT)
        } catch (e: IOException) {
            return Result.Error(RemoteError.NO_INTERNET)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(RemoteError.NO_INTERNET)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return Result.Error(RemoteError.UNKNOWN)
        }
        return response
    }
}