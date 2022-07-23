package com.tlc.weather.app.network.service

import com.tlc.weather.app.model.*
import com.tlc.weather.app.network.api.WeatherApi
import com.tlc.weather.app.network.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class WeatherApiService(
    private val weatherApi: WeatherApi,
    private val networkUtils: NetworkUtils
) : IWeatherApiService {

    override fun getCities(): Flow<NetworkResponse> {
        return flow {
            val result = kotlin.runCatching {
                weatherApi.getCities().execute()
            }
            val networkResponse = networkUtils.parseResponse(result)
            emit(networkResponse)
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetailedWeather(cityId: Long): Flow<NetworkResponse> {
        return flow {
            withContext(Dispatchers.IO) {

                val result = kotlin.runCatching {
                    weatherApi.getDetailedWeather(cityId).execute()
                }
                val networkResponse = networkUtils.parseResponse(result)
                emit(networkResponse)

            }
        }
    }

    companion object {
        private const val TAG = "WeatherApiService"
    }
}