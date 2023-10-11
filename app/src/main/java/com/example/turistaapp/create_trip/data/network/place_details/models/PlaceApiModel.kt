package com.example.turistaapp.create_trip.data.network.place_details.models

import com.google.gson.annotations.SerializedName

data class PlaceApiModel (
    @SerializedName("geometry") val geometryApi: GeometryApiModel,
    val name: String,
    @SerializedName("photos") val photoApi: List<PhotoApiModel>? = listOf(PhotoApiModel("https://www.google.com", 0, 0)),
    val rating: Double,
    @SerializedName("user_ratings_total") val userRatings: Int,
    @SerializedName("vicinity") val address: String,
    @SerializedName("place_id") val placeID: String,
    @SerializedName("types") val types: List<String>
)
