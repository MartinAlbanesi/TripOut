package com.example.turistaapp.core.utils

sealed class Transports(val type : String){
    object Driving : Transports("DRIVING")
    object Walking : Transports("WALKING")
    object Bicycling : Transports("BICYCLING")
}
