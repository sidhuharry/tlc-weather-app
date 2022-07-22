package com.tlc.weather.app.network.api

import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.DetailedWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("weather/list")
    suspend fun getCities(): Call<List<City>>

    @GET("weather/{id}")
    suspend fun getDetailedWeather(@Path("cityId") cityId: Long): Call<City>

}