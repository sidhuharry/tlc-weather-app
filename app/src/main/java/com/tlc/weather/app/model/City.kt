package com.tlc.weather.app.model

import com.google.gson.annotations.SerializedName

data class City(
    val id: Long,
    val name: String,
    @SerializedName(value="coord") val coordinates: Coordinates,
)

