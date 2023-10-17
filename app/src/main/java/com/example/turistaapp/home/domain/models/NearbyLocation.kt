package com.example.turistaapp.home.domain.models

data class NearbyLocation(
    val name: String,
    val photoUrl: String,
    val rating: Double,
    val userRating: Int,
    val direction: String,
    val lat: Double,
    val lng: Double,
)
