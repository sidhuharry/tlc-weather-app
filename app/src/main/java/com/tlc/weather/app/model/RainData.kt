package com.tlc.weather.app.model

import com.google.gson.annotations.SerializedName

data class RainData(@SerializedName("3h") val threeH: Float)