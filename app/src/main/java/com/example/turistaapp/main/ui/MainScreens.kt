package com.example.turistaapp.main.ui

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
import com.example.turistaapp.map.ui.HomeScreen
import com.example.turistaapp.map.ui.viewmodel.HomeViewModel


@Composable
fun MainScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    val nearbyLocations by homeViewModel.nearbyLocations.collectAsStateWithLifecycle()

    val nearbyLocationSelect by homeViewModel.nearbyLocationSelect.collectAsStateWithLifecycle()

    val destinationLocations by homeViewModel.destinationLocations.collectAsStateWithLifecycle()

    val directionSelect by homeViewModel.polyLinesPoints.collectAsStateWithLifecycle()

    val markerSelect by homeViewModel.markerSelect.collectAsStateWithLifecycle()

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
                HomeScreen(
                    nearbyLocations,
                    nearbyLocationSelect,
                    locations = destinationLocations,
                    directionSelect = directionSelect,
                    markerSelect = markerSelect,
                    onClickArrowBack = { homeViewModel.getFlowLocationFromDB() },
                    onMarkerSelected = { homeViewModel.getTripById(it)},
                    onClickFloatingBottom = { navController.navigate(Routes.CreateTrip.route) },
                    onCreateTripDialog = { navController.navigate(Routes.CreateTrip.setArgument(it))},
                ) { homeViewModel.setNearbyLocationSelect(it) }
            }

            Routes.Map.route -> {
                Text(text = "Map")
            }
            Routes.Settings.route -> {
                Text(text = "Settings")
            }
        }
    }
}
