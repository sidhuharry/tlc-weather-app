package com.tlc.weather.app.network.util

import android.util.Log
import com.tlc.weather.app.model.NetworkResponse
import retrofit2.Response

/**
 * Util class to hold util methods so that other services can re-use.
 */
class NetworkUtils {
    fun <T> parseResponse(result: Result<Response<T>>): NetworkResponse {
        var networkResp: NetworkResponse = NetworkResponse.Failure()
        if (result.isSuccess) {
            // This means the operation has succeeded not the call.
            // To check call's success, we have to check the http response code.
            result.getOrNull()?.let {
                networkResp = if (it.isSuccessful) {
                    NetworkResponse.Success(it.code(), it.body())
                } else {
                    NetworkResponse.Failure(it.code(), it.message())
                }
            }
        } else {
            Log.e(TAG, "Unable to fetch cities from server.", result.exceptionOrNull())
        }
        return networkResp
    }

    companion object {
        private const val TAG = "WeatherAppUtils"
    }
}