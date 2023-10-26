package com.example.turistaapp.home.domain.models

import com.example.turistaapp.home.domain.models.directions.RouteModel

data class DirectionsModel(
    val routeModel: List<RouteModel>,
    val status: String
)