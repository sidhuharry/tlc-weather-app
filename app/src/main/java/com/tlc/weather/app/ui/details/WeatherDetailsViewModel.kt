package com.tlc.weather.app.ui.details

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.repo.WeatherRepo
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class WeatherDetailsViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    // private, because you should not expose the MutableLiveData
    private val _isError = MutableLiveData<Boolean>()

    val isError: LiveData<Boolean>
        get() = _isError

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading


    private val _weatherDetails = MutableLiveData<DetailedWeather>()

    val weatherDetails: LiveData<DetailedWeather>
        get() = _weatherDetails

    private val _weatherIcon = MutableLiveData<Bitmap>()

    val weatherIcon: LiveData<Bitmap>
        get() = _weatherIcon


    @OptIn(FlowPreview::class)
    fun loadWeatherDetails(cityId: Long) {
        _isError.value = false
        _isLoading.value = true
        viewModelScope.launch {
            weatherRepo.getDetailedWeather(cityId).catch { exception ->
                Log.e(Companion.TAG, "Get detailed weather failed", exception)
                _isError.postValue(true)
                _isLoading.postValue(false)
            }.flatMapConcat { networkResp ->
                when (networkResp) {
                    is NetworkResponse.Failure -> {
                        return@flatMapConcat flowOf(NetworkResponse.Failure())
                    }
                    is NetworkResponse.Success -> {
                        val resp = networkResp.responseBody as DetailedWeather
                        val iconId = resp.weatherData?.get(0)?.icon
                        _weatherDetails.postValue(resp)
                        return@flatMapConcat weatherRepo.getWeatherIcon(iconId!!)
                    }
                }
            }.collect { networkResp ->
                when (networkResp) {
                    is NetworkResponse.Failure -> {
                        _isError.postValue(true)
                        _isLoading.postValue(false)
                    }
                    is NetworkResponse.Success -> {
                        _isLoading.postValue(false)
                        _isError.postValue(false)

                        val imageInBinary = (networkResp.responseBody as ResponseBody).bytes()
                        val bitmap =
                            BitmapFactory.decodeByteArray(imageInBinary, 0, imageInBinary.size)
                        _weatherIcon.postValue(bitmap)
                    }
                }

            }
        }
    }

    companion object {
        private const val TAG = "WeatherDetailsViewModel"
    }
}