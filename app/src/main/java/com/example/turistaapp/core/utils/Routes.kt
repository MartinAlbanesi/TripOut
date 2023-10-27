package com.example.turistaapp.core.utils

sealed class Routes(val route: String) {
    object Home : Routes("home_screen")
    object Trips : Routes("trip_screen")
    object CreateTrip : Routes("create_trip_screen")
    object Settings : Routes("setting_screen")
}
