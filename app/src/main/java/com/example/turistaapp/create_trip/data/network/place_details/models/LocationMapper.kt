package com.example.turistaapp.create_trip.data.network.place_details.models // ktlint-disable package-name

import com.example.turistaapp.create_trip.domain.models.LocationModel

fun PlaceApiModel.toLocationModel(): LocationModel {
    return LocationModel(
        lat = geometryApi.locationApi.lat,
        lng = geometryApi.locationApi.lng,
        name = name,
        photoUrl = photoApi?.get(0)?.photoUrl,
        rating = rating,
        userRating = userRatings,
        address = address,
        types = types,
        placeID = placeID,
    )
}
