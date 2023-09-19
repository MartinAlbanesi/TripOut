package com.example.turistaapp.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.turistaapp.create_trip.ui.TripForm
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.utils.Routes

@Composable
fun NavHostScreen(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route){ HomeScreen(paddingValues) }
        composable(Routes.CreateTrip.route){ TripForm(innerPadding = paddingValues) }
        composable(Routes.Configuraciones.route){  }
    }
}