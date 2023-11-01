package com.example.turistaapp.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.map.ui.components.NearbySearchView
import com.example.turistaapp.map.ui.components.TripDialog


@Composable
fun HomeScreen(
    nearbyLocations: ResponseUiState,
    nearbyLocationSelect: LocationModel?,
    onCreateTripDialog: (String) -> Unit,
    onCardSelection: (String) -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

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
                    Column(Modifier.fillMaxWidth().height(200.dp)) {
                        Text(text = nearbyLocations.message)
                    }
                }


            }
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