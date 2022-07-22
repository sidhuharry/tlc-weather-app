package com.tlc.weather.app.network.service

import android.net.Network
import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherApiService {

    fun getCities(): Flow<NetworkResponse>

    fun getDetailedWeather(cityId: Long): Flow<NetworkResponse>

}