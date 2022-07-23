package com.tlc.weather.app.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.network.service.IWeatherApiService
import com.tlc.weather.app.network.service.WeatherApiService
import kotlinx.coroutines.flow.Flow

/**
 * Repo layer for weather api.
 * It can be used to cache the response
 */
class WeatherRepo(private val weatherApiService: WeatherApiService) : IWeatherRepo {

    override suspend fun getCities(): Flow<NetworkResponse> {
        return weatherApiService.getCities()
    }

    override suspend fun getDetailedWeather(cityId: Long): Flow<NetworkResponse> {
        return weatherApiService.getDetailedWeather(cityId)
    }

    override suspend fun getWeatherIcon(iconId: String): Flow<NetworkResponse> {
        return weatherApiService.getIcon(iconId)
    }


}