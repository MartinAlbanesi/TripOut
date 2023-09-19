package com.example.turistaapp.utils

sealed class Routes(val route : String){
    object Home : Routes("HomeScreen")
    object Viajes : Routes("ViajesScreen")
    object CreateTrip : Routes("CreateTripScreen")
    object Configuraciones : Routes("ConfiguracionesScreen")
}
