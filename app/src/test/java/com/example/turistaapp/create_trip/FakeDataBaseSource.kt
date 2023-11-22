package com.example.turistaapp.create_trip

import com.example.turistaapp.core.data.database.entities.LocationEntity
import com.example.turistaapp.core.data.database.entities.TripEntity
import com.example.turistaapp.create_trip.domain.models.LocationModel

object FakeDataBaseSource {

    val locationEntity = LocationEntity(
        1,
        "Location 1",
        "Description 1",
        null,
        0.0,
        1,
        -34.6246832,
        -58.487447,
        null,
        null,
    )

    val locationModel = LocationModel(
        "Location 1",
        "Description 1",
        null,
        0.0,
        1,
        -34.6246832,
        -58.487447,
        null,
        null,
    )

    val tripEntityList = listOf(
        TripEntity(
            id = 1,
            name = "Trip 1",
            origin = locationEntity,
            destination = locationEntity,
            stops = null,
            startDate = "2021-01-01",
            endDate = "2021-01-02",
            members = null,
            transport = "Transport 1",
            description = null,
            author = "",
            isFavorite = false,
            isFinished = false,
            images = null,
            comments = null,
        ),
        TripEntity(
            id = 2,
            name = "Trip 2",
            origin = locationEntity,
            destination = locationEntity,
            stops = null,
            startDate = "2021-01-01",
            endDate = "2021-01-02",
            members = null,
            transport = "Transport 2",
            description = null,
            author = "",
            isFavorite = false,
            isFinished = false,
            images = null,
            comments = null,
        ),
        TripEntity(
            id = 3,
            name = "Trip 3",
            origin = locationEntity,
            destination = locationEntity,
            stops = null,
            startDate = "2021-01-01",
            endDate = "2021-01-02",
            members = null,
            transport = "Transport 3",
            description = null,
            author = "",
            isFavorite = false,
            isFinished = false,
            images = null,
            comments = null,
        ),
    )
}
