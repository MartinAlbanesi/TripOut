package com.example.turistaapp.main.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.turistaapp.core.utils.Routes

@Composable
fun BottomBarScreen(
    index: Int,
    navController: NavHostController = rememberNavController(),
    changeIndex: (Int) -> Unit,
) {
    NavigationBar {
        NavigationBarItem(
            selected = index == 1,
            onClick = {
                navController.navigate(Routes.Home.route)
                changeIndex(1)
            },
            icon = {
                Icon(Icons.Outlined.Home, contentDescription = "Home")
            },
        )
        NavigationBarItem(
            selected = index == 2,
            onClick = {
                navController.navigate(Routes.Trips.route)
                changeIndex(2)
            },
            icon = {
                Icon(Icons.Outlined.DirectionsCar, contentDescription = "Viajes")
            },
        )

        NavigationBarItem(
            selected = index == 3,
            onClick = {
                navController.navigate(Routes.Settings.route)
                changeIndex(3)
            },
            icon = {
                Icon(Icons.Outlined.Settings, contentDescription = "Configuraciones")
            },
        )
    }
}
