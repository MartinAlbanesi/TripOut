package com.example.turistaapp.create_trip.data.mapper // ktlint-disable package-name

import com.example.turistaapp.create_trip.data.database.entities.LocationEntity
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceApi
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel

fun TripEntity.toTripModel() = TripModel(
    name = name,
    origin = origin.toLocationModel(name, isFinished),
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
    comments = comments,
)

fun TripModel.toTripEntity() = TripEntity(
    id = 0,
    name = name,
    origin = this.origin.toLocationEntity(),//toLocationEntity(),
    destination = this.destination.toLocationEntity(),
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
    comments = comments,
)


fun LocationEntity.toLocationModel(
    tripName : String = "",
    isFinished : Boolean = false

) = LocationModel(
    placeID = placeID,
    name = name,
    photoUrl = photo,
    rating = rating,
    userRating = userRating,
    address = address,
    lat = lat,
    lng = lng,
    types = types,
    tripName = tripName,
    isFinished = isFinished
)

fun LocationModel.toLocationEntity() = LocationEntity(
    id = 0,
    placeID = placeID,
    name = name,
    photo = photoUrl,
    rating = 0.0,
    userRating = 0,
    address = address,
    lat = lat,
    lng = lng,
    types = types,
)

fun PlaceApi.toLocationModel() = LocationModel(
    lat = geometryApi.locationApi.lat,
    lng = geometryApi.locationApi.lng,
    name = name,
    photoUrl = getPhoto(),
    rating = rating,
    userRating = userRatings,
    address = address,
    types = types,
    placeID = placeID,
)

