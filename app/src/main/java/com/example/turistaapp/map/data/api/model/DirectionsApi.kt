package com.example.turistaapp.map.data.api.model

import com.example.turistaapp.map.data.api.model.directions.RouteApi

data class DirectionsApi(
    val routes: List<RouteApi>,
    val status: String,
)
