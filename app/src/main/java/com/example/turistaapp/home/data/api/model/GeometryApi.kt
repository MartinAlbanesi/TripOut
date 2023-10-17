package com.example.turistaapp.home.data.api.model

import com.google.gson.annotations.SerializedName

data class GeometryApi(
    @SerializedName("location") val locationApi: LocationApi,
)
