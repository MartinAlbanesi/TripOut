package com.example.turistaapp.home.utils

import com.example.turistaapp.create_trip.data.network.place_details.models.LocationApi

fun getLocationString(locationApi: LocationApi): String {
    return locationApi.lat.toString() + "," + locationApi.lng.toString()
}

fun getLocationString(lat: Double, lng: Double): String {
    return "$lat,$lng"
}
