package com.example.turistaapp.create_trip.ui.screens.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.turistaapp.R
@Composable
fun BottomAppBarScreen() {
    BottomAppBar(

    ) {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
        )
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(painter = painterResource(id = R.drawable.car), contentDescription = "MyTrips")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        )
    }
}