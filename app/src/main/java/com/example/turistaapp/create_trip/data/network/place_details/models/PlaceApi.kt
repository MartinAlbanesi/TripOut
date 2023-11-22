package com.example.turistaapp.create_trip.data.network.place_details.models // ktlint-disable package-name

import com.example.turistaapp.core.utils.getPhotosFromMap
import com.google.gson.annotations.SerializedName

data class PlaceApi(
    @SerializedName("geometry") val geometryApi: GeometryApi,
    val name: String,
    @SerializedName("photos") val photoApi: List<PhotoApi>?,
    val rating: Double,
    @SerializedName("user_ratings_total") val userRatings: Int,
    @SerializedName("vicinity") val address: String,
    @SerializedName("place_id") val placeID: String,
    @SerializedName("types") val types: List<String>,
) {
    fun getPhoto(): String? {
        if (photoApi.isNullOrEmpty()) {
            return null
        }

        return getPhotosFromMap(photoApi[0].photoUrl)
    }
}
