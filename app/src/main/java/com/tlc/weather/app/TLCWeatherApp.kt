package com.tlc.weather.app

import android.app.Application
import android.util.Log
import com.tlc.weather.app.di.appModule
import com.tlc.weather.app.di.networkModule
import com.tlc.weather.app.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TLCWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Starting the app with koin.")
        //koin plugs in here
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TLCWeatherApp)
            modules(listOf(networkModule, appModule, repoModule))
        }
    }

    companion object {
        private const val TAG = "TLCWeatherApp"
    }
}