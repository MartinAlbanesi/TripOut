package com.example.turistaapp.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.turistaapp.create_trip.ui.screens.CreateTripScreen
import com.example.turistaapp.core.ui.MainScreen
import com.example.turistaapp.core.utils.enums.Routes

@Composable
fun NavHostScreen(
//    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val navController = rememberNavController()

//    val nearbyLocations by homeViewModel.nearbyLocations.collectAsStateWithLifecycle()
//
//    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()
//
//    val destinationLocations by homeViewModel.destinationLocations.collectAsStateWithLifecycle()
//
//    val directionSelect by homeViewModel.polyLinesPoints.collectAsStateWithLifecycle()
//
//    val markerSelect by homeViewModel.markerSelect.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            MainScreen(navController = navController)
        }
//        composable(Routes.Map.route) {
//            HomeScreen(
//                nearbyLocations,
//                nearbyLocationSelect,
//                locations = destinationLocations!!,
//                directionSelect = directionSelect,
//                markerSelect = markerSelect,
//                onClickArrowBack = { homeViewModel.getFlowLocationFromDB() },
//                onMarkerSelected = { homeViewModel.getTripById(it)},
//                onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
//                onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it))},
//            ) { homeViewModel.setNearbyLocationSelect(it) }
//        }
        composable(
            Routes.CreateTrip.route,
            arguments = listOf(navArgument("address"){defaultValue = ""})
        ) {
            CreateTripScreen(address = it.arguments?.getString("address")) {
                navController.navigate(Routes.Home.route)
            }
        }
//        composable(Routes.Trips.route) { }
//        composable(Routes.Settings.route) { }
    }
}
