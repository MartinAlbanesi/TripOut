package com.example.turistaapp.map.utils

fun calculateZoom(distance: String): Float {

    val km = distance.replace(",", "").replace(" km", "").toFloat()

    val a = 0.0000051f
    val b = -0.0115f
    val c = 11.8f

    return a * km * km + b * km + c
}