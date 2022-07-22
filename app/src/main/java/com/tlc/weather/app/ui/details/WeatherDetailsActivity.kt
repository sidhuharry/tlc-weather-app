package com.tlc.weather.app.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tlc.weather.app.R
import com.tlc.weather.app.databinding.ActivityWeatherDetailsBinding
import com.tlc.weather.app.ui.BaseActivity

class WeatherDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityWeatherDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.toolbarLayout.title = title
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}