package com.example.turistaapp.create_trip.data.network.place_details.models

import com.google.gson.annotations.SerializedName

data class GeometryApiModel(
    @SerializedName("location") val locationApi: LocationApiModel
)