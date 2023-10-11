package com.example.turistaapp.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.turistaapp.create_trip.ui.screens.CreateTripScreen
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.utils.Routes


@Composable
fun NavHostScreen(navController: NavHostController, paddingValues: PaddingValues, createTripViewModel: CreateTripViewModel) {

    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route){ HomeScreen(paddingValues) }
        composable(Routes.CreateTrip.route){ CreateTripScreen(innerPadding = paddingValues, createTripViewModel) }
        composable(Routes.Configuraciones.route){  }
        composable(Routes.Viajes.route){  }
    }
}