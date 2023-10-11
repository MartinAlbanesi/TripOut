package com.example.turistaapp.create_trip.data.network.place_details.models

import com.google.gson.annotations.SerializedName

data class PhotoApiModel(
    @SerializedName("photo_reference") val photoUrl: String,
    val height: Int,
    val width: Int
)
