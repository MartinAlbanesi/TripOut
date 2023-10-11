package com.example.turistaapp.create_trip.domain.models

import com.example.turistaapp.create_trip.data.database.entities.LocationEntity

data class TripModel(

    val name: String,
    val origin: LocationModel?,
    val destination: LocationModel?,
    val startDate: String,
    val endDate: String,
    val transport: String,
    val members: MutableList<String>?,
    val stops: MutableList<LocationModel>?,
    val description: String?,
    val author: String,
    val images: MutableList<String>?,
    val comments: MutableList<String>?,
    val isFavorite: Boolean,
    val isFinished: Boolean
)