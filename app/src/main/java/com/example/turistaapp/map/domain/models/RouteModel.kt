package com.example.turistaapp.map.domain.models

import com.example.turistaapp.create_trip.domain.models.TripModel

data class RouteModel(
    val distance: String,
    val duration: String,
    val points: String,
    val summary: String,
    val trip : TripModel? = null
)