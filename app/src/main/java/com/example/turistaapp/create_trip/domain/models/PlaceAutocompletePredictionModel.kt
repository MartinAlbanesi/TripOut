package com.example.turistaapp.create_trip.domain.models

data class PlaceAutocompletePredictionModel(
    val placeId: String,
    val description: String?,
    val distanceMeters: Int?,
    val types: List<String>
)