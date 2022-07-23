package com.tlc.weather.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlc.weather.app.model.CitiesResponse
import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.repo.IWeatherRepo
import com.tlc.weather.app.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeActivityViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    // private, because you should not expose the MutableLiveData
    private val _isError = MutableLiveData<Boolean>()

    val isError: LiveData<Boolean>
        get() = _isError

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading


    private val _cities = MutableLiveData<CitiesResponse>()

    val cities: LiveData<CitiesResponse>
        get() = _cities


    fun loadCities() {
        _isError.value = false
        _isLoading.value = true
        viewModelScope.launch {
            weatherRepo.getCities().catch { exception ->
                Log.e(TAG, "Get cities failed", exception)
                _isError.postValue(true)
                _isLoading.postValue(false)
            }.collect { networkResp ->

                when (networkResp) {
                    is NetworkResponse.Failure -> {
                        _isError.postValue(true)
                        _isLoading.postValue(false)
                    }
                    is NetworkResponse.Success -> {
                        _isLoading.postValue(false)
                        _isError.postValue(false)
                        _cities.postValue(networkResp.responseBody as CitiesResponse)
                    }
                }

            }
        }
    }

    companion object {
        private const val TAG = "HomeActivityViewModel"
    }

}