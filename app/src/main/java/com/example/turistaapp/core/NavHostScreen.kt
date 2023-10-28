package com.example.turistaapp.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.turistaapp.core.utils.enums.Routes
import com.example.turistaapp.create_trip.ui.screens.CreateTripScreen
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.home.ui.viewmodel.HomeViewModel

@Composable
fun NavHostScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val nearbyLocations by homeViewModel.nearbyLocations.collectAsStateWithLifecycle()

    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()

    val destinationLocations by homeViewModel.destinationLocations.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
//                paddingValues,
                nearbyLocations,
                nearbyLocationSelect,
                locations = destinationLocations,
                onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
            ) { homeViewModel.setNearbyLocationSelect(it) }
        }
        composable(Routes.CreateTrip.route) {
            CreateTripScreen {
                navController.navigate(Routes.Home.route)
            }
        }
        composable(Routes.Trips.route) { }
        composable(Routes.Settings.route) { }
    }
}
