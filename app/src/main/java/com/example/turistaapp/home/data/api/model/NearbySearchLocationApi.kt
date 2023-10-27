package com.example.turistaapp.home.data.api.model

import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceApi
import com.google.gson.annotations.SerializedName

data class NearbySearchLocationApi(
    @SerializedName("results") val nearbyLocationsApi: List<PlaceApi>,
)
