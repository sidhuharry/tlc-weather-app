package com.tlc.weather.app.network.api

import android.media.Image
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenWeatherApi {

    @GET("img/wn/{iconId}@4x.png")
    fun getIcon(@Path("iconId") iconId: String): Call<ResponseBody>

}