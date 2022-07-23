package com.tlc.weather.app.repo

import com.tlc.weather.app.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepo {

    suspend fun getCities(): Flow<NetworkResponse>

    suspend fun getDetailedWeather(cityId: Long): Flow<NetworkResponse>

    suspend fun getWeatherIcon(iconId: String): Flow<NetworkResponse>

}