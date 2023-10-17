package com.example.turistaapp.home.data.api.model

import com.example.turistaapp.core.utils.getPhotosFromMap
import com.google.gson.annotations.SerializedName

data class NearbyLocationApi(
    @SerializedName("geometry") val geometryApi: GeometryApi,
    val name: String,
    @SerializedName("photos") val photoApis: List<PhotoApi>?,
    val rating: Double,
    @SerializedName("user_ratings_total") val userRatings: Int,
    @SerializedName("vicinity") val direction: String,
) {
    fun getLat(): Double {
        return geometryApi.locationApi.lat
    }

    fun getLng(): Double {
        return geometryApi.locationApi.lng
    }

    fun getPhoto(): String {
        return getPhotosFromMap(photoApis?.get(0)?.photoUrl ?: "titi")
    }
}
