package com.tlc.weather.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tlc.weather.app.R

/**
 * Base activity for weather app, facilitates common setup throughout the app.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setup action bar common in the app here
        setSupportActionBar(findViewById(R.id.toolbar))
    }

}