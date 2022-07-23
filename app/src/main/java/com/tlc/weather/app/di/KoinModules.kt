package com.tlc.weather.app.di

import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.network.api.OpenWeatherApi
import com.tlc.weather.app.network.api.WeatherApi
import com.tlc.weather.app.network.service.WeatherApiService
import com.tlc.weather.app.network.util.NetworkUtils
import com.tlc.weather.app.repo.IWeatherRepo
import com.tlc.weather.app.repo.WeatherRepo
import com.tlc.weather.app.ui.details.WeatherDetailsViewModel
import com.tlc.weather.app.ui.home.HomeActivityViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        createWeatherApi()
    }
    single {
        createOpenWeatherApi()
    }
    single {
        NetworkUtils()
    }

    single {
        WeatherApiService(get(), get(), get())
    }

}

val repoModule = module {
    single {
        WeatherRepo(get())
    }
}

val appModule = module {
    viewModel {
        HomeActivityViewModel(get())
    }

    viewModel {
        WeatherDetailsViewModel(get())
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

fun createOpenWeatherApi(): OpenWeatherApi {
    val retrofit =
        Retrofit.Builder().baseUrl("https://openweathermap.org").client(okHttpClient()).build()
    return retrofit.create(OpenWeatherApi::class.java)
}
