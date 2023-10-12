package com.example.turistaapp.core.utils

import com.example.turistaapp.home.data.api.model.LocationApi

fun getLocationString(locationApi : LocationApi) : String{
    return locationApi.lat.toString() + "," + locationApi.lng.toString()
}

fun getLocationString(lat : Double, lng : Double) : String{
    return "$lat,$lng"
}