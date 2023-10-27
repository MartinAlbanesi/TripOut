package com.example.turistaapp.core.ui

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
import com.example.turistaapp.core.utils.Routes
import com.example.turistaapp.map.ui.MapScreen
import com.example.turistaapp.map.ui.viewmodel.MapViewModel


@Composable
fun MainScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    val nearbyLocations by mapViewModel.nearbyLocations.collectAsStateWithLifecycle()

    val nearbyLocationSelect by mapViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()

    val destinationLocations by mapViewModel.destinationLocations.collectAsStateWithLifecycle()

    val directionSelect by mapViewModel.polyLinesPoints.collectAsStateWithLifecycle()

    val markerSelect by mapViewModel.markerSelect.collectAsStateWithLifecycle()

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(
        Routes.Home.route,
        Routes.Map.route,
        Routes.Settings.route
    )
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
        when(titles[state]){
            Routes.Home.route -> {

            }

            Routes.Map.route -> {
                MapScreen(
                    nearbyLocations,
                    nearbyLocationSelect,
                    locations = destinationLocations,
                    directionSelect = directionSelect,
                    markerSelect = markerSelect,
                    onClickArrowBack = { mapViewModel.getFlowLocationFromDB() },
                    onMarkerSelected = { mapViewModel.getTripById(it)},
                    onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
                    onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it))},
                ) { mapViewModel.setNearbyLocationSelect(it) }
            }
            Routes.Settings.route -> {
                Text(text = "Settings")
            }
        }
    }
}
