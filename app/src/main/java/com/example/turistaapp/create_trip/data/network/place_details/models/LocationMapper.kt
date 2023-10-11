package com.example.turistaapp.create_trip.data.network.place_details

import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceApiModel
import com.example.turistaapp.create_trip.domain.models.LocationModel

fun PlaceApiModel.toLocationModel(): LocationModel {
    return LocationModel(
        lat = geometryApi.locationApi.lat,
        lng = geometryApi.locationApi.lng,
        name = name,
        photoUrl = photoApi!![0].photoUrl,
        rating = rating,
        userRating = userRatings,
        direction = direction
    )
}