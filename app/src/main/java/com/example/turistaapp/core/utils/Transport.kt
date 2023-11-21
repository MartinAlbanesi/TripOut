package com.example.turistaapp.core.utils

class Transport(val type : String, val name: String){
}

sealed class Transports(val type: String) {
    object Driving : Transports("driving")
    object Walking : Transports("walking")
    object Bicycling : Transports("bicycling")
}
