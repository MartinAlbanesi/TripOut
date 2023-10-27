package com.example.turistaapp.home.data.mappers

import com.example.turistaapp.home.data.api.model.directions.RouteApi
import com.example.turistaapp.home.domain.models.RouteModel

fun RouteApi.toRouteModel() = RouteModel(
    distance = this.legs[0].distance.text,
    duration = this.legs[0].duration.text,
    points = this.overviewPolyline.points,
    summary = summary
)