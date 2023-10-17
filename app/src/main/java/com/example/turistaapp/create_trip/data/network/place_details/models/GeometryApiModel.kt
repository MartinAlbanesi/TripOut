package com.example.turistaapp.create_trip.data.network.place_details.models // ktlint-disable package-name

import com.google.gson.annotations.SerializedName

data class GeometryApiModel(
    @SerializedName("location") val locationApi: LocationApiModel,
)
