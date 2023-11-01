package com.example.turistaapp.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.ui.components.NearbySearchView
import com.example.turistaapp.map.ui.components.TripDialog
import com.example.turistaapp.my_trips.ui.screens.components.TripItem

@Composable
fun HomeScreen(
    nearbyLocations: ResponseUiState,
    nearbyLocationSelect: LocationModel?,
    myTrips: List<TripModel>,
    onCreateTripDialog: (String) -> Unit,
    onCardSelection: (String) -> Unit,
    onClickFloatingBottom: () -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn() {
            item {
                Text(
                    text = "Descubre más viajes",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
            }
            item {
                when (nearbyLocations) {
                    is ResponseUiState.Success<*> -> {
                        NearbySearchView(
                            nearbyLocations = nearbyLocations.values as List<LocationModel>,
                            onClickCard = {
                                showDialog = true
                                onCardSelection(it)
                            },
                        )
                    }
                    is ResponseUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier
                            .padding(16.dp)
                            .size(100.dp))
                    }
                    is ResponseUiState.Error -> {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp)) {
                            Text(text = nearbyLocations.message)
                        }
                    }


                }
            }
        }
        item {
            Text(
                text = "Mis viajes",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
        }
        items(myTrips) { trip ->
            TripItem(
                name = trip.name,
                photoUrl = trip.destination.photoUrl ?: "",
                startDate = trip.startDate,
                endDate = trip.endDate,
                originName = trip.origin.name,
                destinationName = trip.destination.name,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { },
            )
        }

        FloatingActionButton(
            onClick = { onClickFloatingBottom() },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }

    if (showDialog) {
        if (nearbyLocationSelect != null) {
            TripDialog(
                nearbyLocationSelect = nearbyLocationSelect,
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    onCreateTripDialog(it)
                },
            )
        }
    }
}
