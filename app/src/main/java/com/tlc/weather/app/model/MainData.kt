package com.tlc.weather.app.model

import com.google.gson.annotations.SerializedName

data class MainData(
    val temp: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
    val pressure: Float,
    val humidity: Int
)