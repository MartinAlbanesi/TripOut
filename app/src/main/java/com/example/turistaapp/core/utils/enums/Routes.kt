package com.example.turistaapp.core.utils.enums

sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object Map : Routes("Map")
    object Welcome : Routes("Welcome")
    object CreateTrip : Routes("create_trip_screen?address={address}"){
        fun setArgument(address : String) = "create_trip_screen?address=$address"
    }
    object Settings : Routes("Setting")
}
