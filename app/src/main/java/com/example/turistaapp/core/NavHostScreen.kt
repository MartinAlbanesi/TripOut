package com.example.turistaapp.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.turistaapp.core.utils.Routes
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

    val directionSelect by homeViewModel.polyLinesPoints.collectAsStateWithLifecycle()

    val markerSelect by homeViewModel.markerSelect.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
                nearbyLocations,
                nearbyLocationSelect,
                locations = destinationLocations!!,
                directionSelect = directionSelect,
                markerSelect = markerSelect,
                onClickArrowBack = { homeViewModel.getFlowLocationFromDB() },
                onMarkerSelected = { homeViewModel.getTripById(it)},
                onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
                onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it))},
            ) { homeViewModel.setNearbyLocationSelect(it) }
        }
        composable(
            Routes.CreateTrip.route,
            arguments = listOf(navArgument("address"){defaultValue = ""})
        ) {
            CreateTripScreen(address = it.arguments?.getString("address")) {
                navController.navigate(Routes.Home.route)
            }
        }
        composable(Routes.Trips.route) { }
        composable(Routes.Settings.route) { }
    }
}
