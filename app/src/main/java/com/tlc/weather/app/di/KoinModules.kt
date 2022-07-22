package com.tlc.weather.app.di

import com.tlc.weather.app.network.api.WeatherApi
import com.tlc.weather.app.network.service.WeatherApiService
import com.tlc.weather.app.network.util.NetworkUtils
import com.tlc.weather.app.repo.WeatherRepo
import com.tlc.weather.app.ui.home.HomeActivityViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    viewModel {
        HomeActivityViewModel(get())
    }
}

val networkModule = module {
    single {
        createWeatherApi()
    }
    single {
        WeatherApiService(get(), get())
    }

    single {
        NetworkUtils()
    }
}

val repoModule = module {
    single {
        WeatherRepo(get())
    }
}

private fun okHttpClient() = OkHttpClient.Builder().build()


fun createWeatherApi(): WeatherApi {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient())
        .baseUrl("https://testapi.io/api/olestang/")
        .build()
    return retrofit.create(WeatherApi::class.java);
}
