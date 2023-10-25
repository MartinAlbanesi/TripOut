package com.example.turistaapp.home.data.mappers

import com.example.turistaapp.home.data.api.model.DirectionsApi
import com.example.turistaapp.home.data.api.model.directions.DistanceDurationApi
import com.example.turistaapp.home.data.api.model.directions.LegApi
import com.example.turistaapp.home.data.api.model.directions.OverviewPolylineApi
import com.example.turistaapp.home.data.api.model.directions.RouteApi
import com.example.turistaapp.home.domain.models.DirectionsModel
import com.example.turistaapp.home.domain.models.directions.DistanceDurationModel
import com.example.turistaapp.home.domain.models.directions.OverviewPolylineModel
import com.example.turistaapp.home.domain.models.directions.RouteModel

fun DirectionsApi.toDirectionsModel() = DirectionsModel(
    routeModel = routes.map { it.toRouteModel() },
    status = status
)

fun RouteApi.toRouteModel() = RouteModel(
    legModels = legs.map { it.toLegsModel() },
    overviewPolylineModel = overviewPolyline.toOverviewPolylineModel(),
    summary = summary
)

fun LegApi.toLegsModel() = com.example.turistaapp.home.domain.models.directions.LegModel(
    distance = distance.toDistanceDurationModel(),
    duration = duration.toDistanceDurationModel(),
)

fun DistanceDurationApi.toDistanceDurationModel() = DistanceDurationModel(
    text = text,
    value = value
)

fun OverviewPolylineApi.toOverviewPolylineModel() = OverviewPolylineModel(
    points = points
)