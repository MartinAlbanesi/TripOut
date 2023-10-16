package com.example.turistaapp.home.fake

import com.example.turistaapp.home.data.api.model.GeometryApi
import com.example.turistaapp.home.data.api.model.LocationApi
import com.example.turistaapp.home.data.api.model.NearbyLocationApi
import com.example.turistaapp.home.data.api.model.NearbySearchLocationApi
import com.example.turistaapp.home.domain.models.NearbyLocation

object FakeDataSource {

    val fakeNearbyLocationApi = NearbySearchLocationApi(
        nearbyLocationApis = listOf(
            NearbyLocationApi(
                geometryApi = GeometryApi(LocationApi(0.0, 0.0)),
                name = "name1",
                photoApis = null,
                rating = 0.0,
                userRatings = 0,
                direction = "direction"
            )
        )
    )


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