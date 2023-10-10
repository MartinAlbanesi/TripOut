package com.example.turistaapp.create_trip.domain.models

data class PlaceAutocompleteModel(
    val description: String?,
    val distance_meters: Int?,
    val types: List<String>
)