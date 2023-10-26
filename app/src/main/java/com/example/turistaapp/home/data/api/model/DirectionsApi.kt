package com.example.turistaapp.home.data.api.model

import com.example.turistaapp.home.data.api.model.directions.RouteApi

data class DirectionsApi(
    val routes: List<RouteApi>,
    val status: String
)