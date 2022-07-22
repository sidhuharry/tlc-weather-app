package com.tlc.weather.app.model

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName(value = "cnt") val count: Int,
    @SerializedName(value = "list") val cityList: List<City>
)