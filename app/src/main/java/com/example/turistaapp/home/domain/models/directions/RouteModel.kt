package com.example.turistaapp.home.domain.models.directions

data class RouteModel(
    val legModels: List<LegModel>,
    val overviewPolylineModel: OverviewPolylineModel,
    val summary: String,
)