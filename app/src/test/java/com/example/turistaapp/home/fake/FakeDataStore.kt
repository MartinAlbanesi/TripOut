package com.example.turistaapp.home.fake

import com.example.turistaapp.home.domain.models.NearbyLocation

object FakeDataStore {

    val fakeNearbyLocations = listOf(
        NearbyLocation(
            name = "name1",
            photoUrl = "photoUrl",
            rating = 0.0,
            userRating = 0,
            direction = "direction",
            lat = 0.0,
            lng = 0.0,
        ),
        NearbyLocation(
            name = "name2",
            photoUrl = "photoUrl",
            rating = 0.0,
            userRating = 0,
            direction = "direction",
            lat = 0.0,
            lng = 0.0
        ),
        NearbyLocation(
            name = "name3",
            photoUrl = "photoUrl",
            rating = 0.0,
            userRating = 0,
            direction = "direction",
            lat = 0.0,
            lng = 0.0
        )
    )
}