package com.tlc.weather.app.ui.details

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tlc.weather.app.R
import com.tlc.weather.app.databinding.ActivityWeatherDetailsBinding
import com.tlc.weather.app.model.DetailedWeather
import com.tlc.weather.app.ui.BaseActivity
import com.tlc.weather.app.ui.UiConstants
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityWeatherDetailsBinding

    private lateinit var viewModel: WeatherDetailsViewModel

    private var cityId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.title_activity_weather_details)
        setSupportActionBar(toolbar)

        viewModel = getViewModel()

        viewModel.weatherDetails.observe(this, weatherDetailObserver)
        viewModel.isError.observe(this, errorObserver)
        viewModel.weatherIcon.observe(this, iconObserver)
        viewModel.isLoading.observe(this, loaderObserver)

        cityId = intent.getLongExtra(UiConstants.CITY_ID, -1)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadWeatherDetails(cityId)
    }

    private val errorObserver = Observer<Boolean> { isError ->
        //show error in the UI here
        if (isError) {
            val parentLayout = findViewById<View>(android.R.id.content)
            Snackbar.make(
                parentLayout,
                getString(R.string.error_desc_detailed_weather),
                Snackbar.LENGTH_LONG
            )
                .also {
                    it.setTextMaxLines(4)
                }.show()
        }
    }

    private val loaderObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            binding.progress.show()
        } else {
            binding.progress.hide()
        }
    }

    private val iconObserver = Observer<Bitmap> { bitmap ->
        binding.weatherIcon.setImageBitmap(bitmap)
    }

    private val weatherDetailObserver = Observer<DetailedWeather> {
        updateUi(it)
    }

    private fun updateUi(detailedWeather: DetailedWeather) {
        binding.temp.text = "${detailedWeather.mainData.temp} ℉"
        binding.humidityValue.text = "${detailedWeather.mainData.humidity} g/kg"
        binding.maxTempValue.text = "${detailedWeather.mainData.tempMax} ℉"
        binding.minTempValue.text = "${detailedWeather.mainData.tempMin} ℉"
        binding.pressureValue.text = "${detailedWeather.mainData.pressure}"
        binding.windDegreeValue.text = "${detailedWeather.windData.deg}°"
        binding.windSpeedValue.text = "${detailedWeather.windData.speed} km/h"

        binding.weatherString.text = detailedWeather?.weatherData?.get(0)?.main

        val parseFormat = SimpleDateFormat("E MMMM dd,yyyy")
        Date().also {
            binding.date.text = parseFormat.format(it)
        }
    }

    companion object {
        private const val TAG = "WeatherDetailsActivity"
    }
}