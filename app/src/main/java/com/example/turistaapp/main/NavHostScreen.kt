package com.example.turistaapp.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.core.utils.Routes
import com.example.turistaapp.create_trip.ui.screens.CreateTripScreen
import com.example.turistaapp.create_trip.ui.viewmodels.CreateTripViewModel
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.home.ui.HomeViewModel


@Composable
fun NavHostScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    createTripViewModel: CreateTripViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val nearbyLocations by homeViewModel.nearbyLocations.collectAsState(ResponseUiState.Loading)

    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsState(null)


    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route){
            HomeScreen(
                paddingValues,
                nearbyLocations,
                nearbyLocationSelect
            ) { homeViewModel.setNearbyLocationSelect(it) }
        }
        composable(Routes.CreateTrip.route){ CreateTripScreen(innerPadding = paddingValues, createTripViewModel) }
        composable(Routes.Settings.route){  }
        composable(Routes.Trips.route){  }
    }
}