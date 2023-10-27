package com.example.turistaapp.map.fake

import com.example.turistaapp.create_trip.data.network.place_details.models.GeometryApi
import com.example.turistaapp.create_trip.data.network.place_details.models.LocationApi
import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceApi
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.map.data.api.model.NearbySearchLocationApi


object FakeDataSource {

    val fakeNearbyLocationApi = NearbySearchLocationApi(
        nearbyLocationsApi = listOf(
            PlaceApi(
                geometryApi = GeometryApi(LocationApi(0.0, 0.0)),
                name = "name1",
                photoApi = null,
                rating = 0.0,
                userRatings = 0,
                address = "direction",
                placeID = "",
                types = listOf(""),
            ),
        ),
    )

    val fakeNearbyLocations = listOf(
        LocationModel(
            name = "name1",
            photoUrl = "photoUrl",
            rating = 0.0,
            userRating = 0,
            address = "direction",
            lat = 0.0,
            lng = 0.0,
            placeID = "",
            types = listOf(""),
        ),
        LocationModel(
            name = "name1",
            photoUrl = "photoUrl",
            rating = 0.0,
            userRating = 0,
            address = "direction",
            lat = 0.0,
            lng = 0.0,
            placeID = "",
            types = listOf(""),
        ),
        LocationModel(
            name = "name1",
            photoUrl = "photoUrl",
            rating = 0.0,
            userRating = 0,
            address = "direction",
            lat = 0.0,
            lng = 0.0,
            placeID = "",
            types = listOf(""),
        ),
    )
}
