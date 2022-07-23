package com.tlc.weather.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tlc.weather.app.R
import com.tlc.weather.app.databinding.ActivityHomeBinding
import com.tlc.weather.app.model.CitiesResponse
import com.tlc.weather.app.model.City
import com.tlc.weather.app.ui.BaseActivity
import com.tlc.weather.app.ui.UiConstants
import com.tlc.weather.app.ui.details.WeatherDetailsActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewModel: HomeActivityViewModel

    private lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.home_activity_title)
        toolbar.subtitle = getString(R.string.home_activity_subtitle)
        setSupportActionBar(toolbar)

        viewModel = getViewModel()

        viewModel.cities.observe(this, cityListObserver)
        viewModel.isLoading.observe(this, loaderObserver)
        viewModel.isError.observe(this, errorObserver)

        cityAdapter = CityAdapter { city -> onCityItemClick(city) }
        val citiesRecyclerView: RecyclerView = binding.cityListRecyclerView
        citiesRecyclerView.layoutManager = LinearLayoutManager(this)
        citiesRecyclerView.adapter = cityAdapter

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCities()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private val errorObserver = Observer<Boolean> { isError ->
        //show error in the UI here
        if (isError) {
            val parentLayout = findViewById<View>(android.R.id.content)
            Snackbar.make(parentLayout, getString(R.string.error_desc_cities), Snackbar.LENGTH_LONG)
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

    private val cityListObserver = Observer<CitiesResponse> { citiesResp ->
        //update the city data here
        Log.d(TAG, "Got the cities ${citiesResp.cityList}")

        if (this::cityAdapter.isInitialized) {
            cityAdapter.submitList(citiesResp.cityList)
            cityAdapter.notifyDataSetChanged()
            Log.d(TAG, "Items are ${cityAdapter.itemCount}")
        }
    }

    private fun onCityItemClick(city: City) {
        // move to detailed weather activity
        val intent = Intent(this, WeatherDetailsActivity()::class.java)
        intent.putExtra(UiConstants.CITY_ID, city.id)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}