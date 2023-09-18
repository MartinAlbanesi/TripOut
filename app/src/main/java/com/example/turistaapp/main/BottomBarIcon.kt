package com.example.turistaapp.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.turistaapp.utils.Routes

sealed class BottomBarIcon(
    val route : String,
    val icon : ImageVector,
    val index : Int
){
    object Home : BottomBarIcon(
        route = Routes.Home.route,
        icon = Icons.Outlined.Home,
        1
    )

    object Trips : BottomBarIcon(
        route = Routes.Viajes.route,
        icon = Icons.Outlined.DirectionsCar,
        2
    )

    object Setting : BottomBarIcon(
        route = Routes.Configuraciones.route,
        icon = Icons.Outlined.Settings,
        3
    )
}
