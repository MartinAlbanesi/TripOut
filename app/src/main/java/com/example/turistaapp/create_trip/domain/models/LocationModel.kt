package com.example.turistaapp.create_trip.domain.models

data class LocationModel(
    val placeID: String,
    val name: String,
    val photoUrl: String?,
    val rating: Double,
    val userRating : Int,
    val address : String?,
    val lat: Double,
    val lng: Double,
    val types: List<String>?
)