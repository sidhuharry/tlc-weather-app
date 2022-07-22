package com.tlc.weather.app.model

import com.google.gson.annotations.SerializedName

data class DetailedWeather(
    val city: City,
    @SerializedName(value = "main") val mainData: MainData,
    @SerializedName(value = "dt") val dateTimestamp: Long,
    @SerializedName(value = "wind") val windData: WindData,
    @SerializedName(value = "rain") val rainData: RainData,
    @SerializedName(value = "clouds") val cloudData: CloudData,
    @SerializedName(value = "weather") val weatherData: List<WeatherData>,
    @SerializedName(value = "sys") val sysData: SysData
)