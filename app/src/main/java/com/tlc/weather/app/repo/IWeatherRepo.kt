package com.tlc.weather.app.repo

import androidx.lifecycle.LiveData
import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.model.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepo {

    suspend fun getCities(): Flow<NetworkResponse>

    suspend fun getDetailedWeather(cityId: Long): Flow<NetworkResponse>

}