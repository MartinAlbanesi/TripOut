package com.example.turistaapp.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.turistaapp.create_trip.ui.TripForm
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.core.utils.Routes
import com.example.turistaapp.home.data.api.model.LocationApi
import com.example.turistaapp.home.ui.HomeViewModel

@Composable
fun NavHostScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val nearbyLocations by homeViewModel.nearbyLocations.collectAsState(emptyList())

    val loc = LocationApi(-34.67113975510375, -58.56181551536259)

    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route){
//            homeViewModel.getNearbyLocations(loc)
            HomeScreen(paddingValues, nearbyLocations)
        }
        composable(Routes.CreateTrip.route){ TripForm(innerPadding = paddingValues) }
        composable(Routes.Trips.route){  }
        composable(Routes.Settings.route){  }
    }
}