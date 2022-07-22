package com.tlc.weather.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlc.weather.app.model.City
import com.tlc.weather.app.model.NetworkResponse
import com.tlc.weather.app.repo.WeatherRepo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeActivityViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    // private, because you should not expose the MutableLiveData
    private val _isError = MutableLiveData<Boolean>()

    val isError: LiveData<Boolean>
        get() = _isError

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading


    private val _cities = MutableLiveData<List<City>>()

    val cities: LiveData<List<City>>
        get() = _cities


    fun loadCities() {
        _isError.value = false
        _isLoading.value = true
        viewModelScope.launch {
            _isLoading.value = true
            weatherRepo.getCities().catch { exception ->
                Log.e(Companion.TAG, "Get cities failed", exception)
                _isError.value = true
                _isLoading.value = false
            }.collect { networkResp ->

                when (networkResp) {
                    is NetworkResponse.Failure -> {
                        _isError.value = true
                        _isLoading.value = false
                    }
                    is NetworkResponse.Success -> {
                        _isLoading.value = false
                        _isError.value = false
                        _cities.value = networkResp.responseBody as List<City>
                    }
                }

            }
        }
    }

    companion object {
        private const val TAG = "HomeActivityViewModel"
    }

}