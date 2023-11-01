package com.example.turistaapp.create_trip.domain.models // ktlint-disable package-name

data class PlaceAutocompletePredictionModel(
    val placeId: String,
    val description: String?,
    val distanceMeters: Int?,
    val types: List<String>,
    val structured_formatting: PlaceAutocompleteStructuredFormatModel?,
)
