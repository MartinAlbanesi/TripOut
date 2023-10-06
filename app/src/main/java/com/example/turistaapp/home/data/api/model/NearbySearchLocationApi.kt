package com.example.turistaapp.home.data.api.model

import com.google.gson.annotations.SerializedName

data class NearbySearchLocationApi(
    @SerializedName("results") val nearbyLocationApis: List<NearbyLocationApi>
)