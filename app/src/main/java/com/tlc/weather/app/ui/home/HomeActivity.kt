package com.tlc.weather.app.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tlc.weather.app.R
import com.tlc.weather.app.databinding.ActivityHomeBinding
import com.tlc.weather.app.model.City
import com.tlc.weather.app.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private var viewModel: HomeActivityViewModel by viewModel<HomeActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        viewModel = ViewModelProvider(this)[HomeActivityViewModel::class.java]

        viewModel.cities.observe(this, cityListObserver)
        viewModel.isLoading.observe(this, loaderObserver)
        viewModel.isError.observe(this, errorObserver)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCities()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    val errorObserver = Observer<Boolean> { isError ->
        //show error in the UI here
    }

    val loaderObserver = Observer<Boolean> { isLoading ->
        // hide and show loader here
    }

    val cityListObserver = Observer<List<City>> { cities ->
        //update the city data here

        Log.e(Companion.TAG, "Got the cities ${cities}")
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}