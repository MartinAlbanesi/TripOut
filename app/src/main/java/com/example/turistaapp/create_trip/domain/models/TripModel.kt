package com.example.turistaapp.create_trip.domain.models

import com.google.android.gms.maps.model.LatLng

data class TripModel(
    val tripId: Int = 0,
    val name: String,
    val origin: LocationModel,
    val destination: LocationModel,
    val startDate: String,
    val endDate: String,
    val transport: String,
    val members: MutableList<String>?,
    val stops: MutableList<LocationModel>?,
    val description: String?,
    val author: String,
    val images: List<String>?,
    val comments: MutableList<String>?,
    val isFavorite: Boolean,
    val isFinished: Boolean,
) {
    fun getDestinationWithTripId(): LocationModel {
        return this.destination.copy(tripId = this.tripId)
    }

    fun getOriginWithTripId(): LocationModel {
        return this.origin.copy(tripId = this.tripId)
    }

    fun getLatLngOrigin(): String {
        return "${this.origin.lat},${this.origin.lng}"
    }

    fun getLatLngDestination(): String {
        return "${this.destination.lat},${this.destination.lng}"
    }

    fun getHalfWay(): LatLng {
        val lat = (this.origin.lat + this.destination.lat) / 2
        val lng = (this.origin.lng + this.destination.lng) / 2
        return LatLng(lat, lng)
    }
}
