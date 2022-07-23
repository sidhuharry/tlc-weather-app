package com.tlc.weather.app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tlc.weather.app.R
import com.tlc.weather.app.model.City

class CityAdapter(private val onItemClick: (City) -> Unit) :
    ListAdapter<City, CityViewHolder>(CityDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.bind(city)
    }
}

class CityViewHolder(itemView: View, private val onItemClick: (City) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val cityTextView: TextView = itemView.findViewById(R.id.cityName)
    private val coordinatesTextView: TextView = itemView.findViewById(R.id.cityCoordinates)
    private var currentCity: City? = null

    init {
        itemView.setOnClickListener {
            currentCity?.let {
                onItemClick(it)
            }
        }
    }

    fun bind(city: City) {
        currentCity = city

        cityTextView.text = city.name
        coordinatesTextView.text = "${city.coordinates.lat}, ${city.coordinates.lon}"
    }

}

object CityDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

}