package com.example.turistaapp.create_trip.data.mapper

import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import com.example.turistaapp.create_trip.domain.models.TripModel

fun TripEntity.toTripModel() = TripModel(
    id = id,
    name = name,
    origin = origin,
    destination = destination,
    stops = stops,
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

fun TripModel.toTripEntity() = TripEntity(
    id = id,
    name = name,
    origin = origin,
    destination = destination,
    stops = stops,
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