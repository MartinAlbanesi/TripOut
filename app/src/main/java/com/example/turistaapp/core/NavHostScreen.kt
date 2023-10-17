package com.example.turistaapp.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.core.utils.Routes
import com.example.turistaapp.create_trip.ui.screens.CreateTripScreen
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.home.ui.viewmodel.HomeViewModel
import com.example.turistaapp.main.MainViewModel

@Composable
fun NavHostScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val nearbyLocations by homeViewModel.nearbyLocations.collectAsStateWithLifecycle()

    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()

    val destinationLocations by homeViewModel.destinationLocations.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route) {
            HomeScreen(
                paddingValues,
                nearbyLocations,
                nearbyLocationSelect,
                setShowBottomBar = {
                    mainViewModel.setShowBottomBar(true)
                },
                setShowFloatingActionButton = {
                    mainViewModel.setShowFloatingActionButton(true)
                },
                locations = destinationLocations,
                setTitle = {
                    mainViewModel.setTitle("Home")
                }
            ) { homeViewModel.setNearbyLocationSelect(it) }
        }
        composable(Routes.CreateTrip.route){ CreateTripScreen(innerPadding = paddingValues) }
        composable(Routes.Trips.route){  }
        composable(Routes.Settings.route){  }
    }
}