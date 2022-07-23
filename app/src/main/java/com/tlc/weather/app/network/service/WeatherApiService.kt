package com.tlc.weather.app.network.service

import android.graphics.Bitmap
import com.tlc.weather.app.model.*
import com.tlc.weather.app.network.api.OpenWeatherApi
import com.tlc.weather.app.network.api.WeatherApi
import com.tlc.weather.app.network.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class WeatherApiService(
    private val weatherApi: WeatherApi,
    private val networkUtils: NetworkUtils,
    private val openWeatherApi: OpenWeatherApi
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

            val result = kotlin.runCatching {
                weatherApi.getDetailedWeather(cityId).execute()
            }
            val networkResponse = networkUtils.parseResponse(result)
            emit(networkResponse)

        }.flowOn(Dispatchers.IO)
    }

    override fun getIcon(iconId: String): Flow<NetworkResponse> {
        return flow {
            val result = kotlin.runCatching {
                openWeatherApi.getIcon(iconId).execute()
            }
            val networkResponse = networkUtils.parseResponse(result)
            emit(networkResponse)
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val TAG = "WeatherApiService"
    }
}