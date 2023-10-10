package com.example.turistaapp.create_trip.domain.models

data class PlaceAutocompletePredictionModel(
    val description: String?,
    val distance_meters: Int?,
    val types: List<String>
)