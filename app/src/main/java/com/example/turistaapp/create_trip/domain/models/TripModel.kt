package com.example.turistaapp.create_trip.domain.models

import com.example.turistaapp.create_trip.data.database.entities.LocationEntity

data class TripModel(

    val id: Int,
    val name: String,
    val origin: LocationEntity,
    val destination: LocationEntity,
    val stops: MutableList<LocationEntity>,
    val startDate: String,
    val endDate: String,
    val members: MutableList<String>?,
    val transport: String,
    val description: String?,
    val author: String,
    val isFavorite: Boolean,
    val isFinished: Boolean,
    val images: MutableList<String>?,
    val comments: MutableList<String>?
)