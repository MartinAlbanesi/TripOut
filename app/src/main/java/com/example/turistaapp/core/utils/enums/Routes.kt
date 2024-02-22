package com.example.turistaapp.core.utils.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val icon: ImageVector? = null) {
    object Home : Routes("Home", icon = Icons.Default.Home)
    object Map : Routes("Map", icon = Icons.Default.Map)
    object Welcome : Routes("Welcome")
    object ShakeGame : Routes("ShakeGame")
    object CreateTrip : Routes("create_trip_screen?address={address}") {
        fun setArgument(address: String) = "create_trip_screen?address=$address"
    }
    object SelectedLocationMap : Routes("selected_location_map?location={location}") {
        fun setArgument(location: String) = "selected_location_map?location=$location"
    }
    object Settings : Routes("Setting", icon = Icons.Default.Settings)
}
