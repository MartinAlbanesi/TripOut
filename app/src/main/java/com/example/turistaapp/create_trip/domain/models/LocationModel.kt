package com.example.turistaapp.create_trip.domain.models // ktlint-disable package-name

data class LocationModel(
    val placeID: String,
    val name: String,
    val address: String?,
    val rating: Double,
    val userRating: Int,
    val lat: Double,
    val lng: Double,
    val photoUrl: String?,
    val types: List<String>?,
    val isFinished: Boolean = false,
    val tripName: String = "titi"
)
