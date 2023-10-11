package com.example.turistaapp.create_trip.data.mapper

import com.example.turistaapp.create_trip.data.database.entities.LocationEntity
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel

fun TripEntity.toTripModel() = TripModel(
    name = name,
    origin = origin.toLocationModel(),
    destination = destination.toLocationModel(),
    stops = stops?.map { it.toLocationModel() }?.toMutableList(),
    startDate = startDate,
    endDate = endDate,
    members = members,
    transport = transport,
    description = description,
    author = author,
    isFavorite = isFavorite,
    isFinished = isFinished,
    images = images,
    comments = comments
)

fun TripModel.toTripEntity() = origin?.let {
    destination?.let { it1 ->
        TripEntity(
        id = 0,
        name = name,
        origin = it.toLocationEntity(),
        destination = it1.toLocationEntity(),
        stops = stops?.map { it.toLocationEntity() }?.toMutableList(),
        startDate = startDate,
        endDate = endDate,
        members = members,
        transport = transport,
        description = description,
        author = author,
        isFavorite = isFavorite,
        isFinished = isFinished,
        images = images,
        comments = comments
    )
    }
}

fun LocationEntity.toLocationModel() = LocationModel(
    placeID = placeID,
    name = name,
    photoUrl = photo,
    rating = rating,
    userRating = userRating,
    address = address,
    lat = latitude,
    lng = longitude,
    types = types
)

fun LocationModel.toLocationEntity() = LocationEntity(
    id = 0,
    placeID = placeID,
    name = name,
    photo = photoUrl,
    rating = 0.0,
    userRating = 0,
    address = address,
    latitude = lat,
    longitude = lng,
    types = types
)

