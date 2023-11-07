package com.example.turistaapp.core.ui

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.turistaapp.core.utils.enums.Routes
import com.example.turistaapp.home.ui.HomeScreen
import com.example.turistaapp.home.ui.HomeViewModel
import com.example.turistaapp.map.ui.MapScreen
import com.example.turistaapp.map.ui.viewmodel.MapViewModel
import com.example.turistaapp.my_trips.ui.viewmodels.MyTripsViewModel
import com.example.turistaapp.setting.ui.SettingsScreen
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun MainScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    myTripsViewModel: MyTripsViewModel = hiltViewModel(),
    navController: NavHostController,
    onClickChangeTheme: () -> Unit,
) {
    // Home
    val nearbyLocations by homeViewModel.nearbyLocations.collectAsStateWithLifecycle()

    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()

    val locationSelect by homeViewModel.locationSelect.collectAsStateWithLifecycle()

    // Map
    val destinationLocations by mapViewModel.destinationLocations.collectAsStateWithLifecycle()

    val directionSelect by mapViewModel.polyLinesPoints.collectAsStateWithLifecycle()

    val markerSelect by mapViewModel.markerSelect.collectAsStateWithLifecycle()

    val lastLocation by mapViewModel.lastLocation.collectAsStateWithLifecycle()

    val routeModel by mapViewModel.tripSelected.collectAsStateWithLifecycle()

    // Trips
    val myTrips by myTripsViewModel.trips.collectAsStateWithLifecycle()

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result -> Log.i(ContentValues.TAG, "scanned code: ${result.contents}") },
    )

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
        Routes.Home.route,
        Routes.Map.route,
        Routes.Settings.route,
    )
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index },
                )
            }
        }
        when (titles[state]) {
            Routes.Home.route -> {
                HomeScreen(
                    nearbyLocations = nearbyLocations,
                    nearbyLocationSelect = nearbyLocationSelect,
                    myTrips = myTrips,
                    locationSelect = locationSelect,
                    onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it)) },
                    onCardSelection = { homeViewModel.setNearbyLocationSelect(it) },
                    onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
                    onClickShakeGame = { navController.navigate(Routes.ShakeGame.route) },
                    onQRButtonClick = {
                        scanLauncher.launch(ScanOptions())
                    },
                )
            }

            Routes.Map.route -> {
                MapScreen(
                    locations = destinationLocations,
                    directionSelect = directionSelect,
                    markerSelect = markerSelect,
                    lastLocation = lastLocation,
                    routeModel = routeModel,
                    onClickArrowBack = { mapViewModel.getFlowLocationFromDB() },
                    onMarkerSelected = { mapViewModel.getTripById(it) },
                )
            }

            Routes.Settings.route -> {
                SettingsScreen() {
                    onClickChangeTheme()
                }
            }
        }
    }
}
