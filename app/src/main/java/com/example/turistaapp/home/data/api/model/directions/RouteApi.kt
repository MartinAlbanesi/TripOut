package com.example.turistaapp.home.data.api.model.directions

import com.google.gson.annotations.SerializedName

data class RouteApi(
    val legs: List<LegApi>,
    @SerializedName("overview_polyline") val overviewPolyline: OverviewPolylineApi,
    val summary: String,
)