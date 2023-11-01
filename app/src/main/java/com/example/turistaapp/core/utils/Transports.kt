package com.example.turistaapp.core.utils

sealed class Transports(val type : String){
    object Driving : Transports("driving")
    object Walking : Transports("walking")
    object Bicycling : Transports("bicycling")
}
