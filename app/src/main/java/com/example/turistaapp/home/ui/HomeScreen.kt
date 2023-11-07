package com.example.turistaapp.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.ui.components.NearbySearchView
import com.example.turistaapp.map.ui.components.TripDialog
import com.example.turistaapp.my_trips.ui.screens.components.ImageWithBrush
import com.example.turistaapp.my_trips.ui.screens.components.TripItem

@Composable
fun HomeScreen(
    nearbyLocations: ResponseUiState,
    nearbyLocationSelect: LocationModel?,
    myTrips: List<TripModel>,
    locationSelect: String = "",
    onCreateTripDialog: (String) -> Unit,
    onCardSelection: (String) -> Unit,
    onClickFloatingBottom: () -> Unit,
    onClickShakeGame: () -> Unit,
    onQRButtonClick: () -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    var showFloatingButtons by remember {
        mutableStateOf(false)
    }

    var isQRDialogOpen by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            item {
                ImageWithBrush(
                    name = "Descubra su siguiente viaje",
                    photoUrl = R.drawable.ic_launcher_foreground,
                    padding = 8.dp,
                    textCenter = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            onClickShakeGame()
                        },
                )
            }
            item {
                Column {
                    Text(
                        text = "Descubre más viajes",
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    )
                    Text(
                        text = "Cerca de $locationSelect",
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    )
                }
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .size(240.dp, 360.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(Modifier.size(100.dp))
                        }
                    }

                    is ResponseUiState.Error -> {
                        Column(
                            Modifier
                                .fillMaxWidth(),
                        ) {
                            Text(text = nearbyLocations.message)
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
                    trip = trip,
                    isDialogOpen = isQRDialogOpen,
                    onDismissDialog = { isQRDialogOpen = false },
                    onQRButtonClick = {
                        isQRDialogOpen = true
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
//                        .clickable { },
                )
            }
        }

        AnimatedVisibility(
            visible = showFloatingButtons,
            enter = expandVertically(expandFrom = Alignment.Top) { 0 },
            // Shrinks the content to half of its full height via an animation.
            exit = shrinkVertically(shrinkTowards = Alignment.Bottom) { 0 },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
                .offset(y = (-80).dp),
        ) {
            Column {
                ExtendedFloatingActionButton(
                    onClick = { onClickFloatingBottom() },
                    icon = { Icon(Icons.Default.Map, contentDescription = "Create Trip Screen") },
                    text = { Text(text = "Formulario") },
                    modifier = Modifier,
//                    .padding(bottom = 16.dp, end = 16.dp)
//                    .align(Alignment.BottomEnd),
//                    .offset(y = (-80).dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExtendedFloatingActionButton(
                    onClick = {
                        onQRButtonClick()
                    },
                    icon = { Icon(Icons.Default.QrCodeScanner, contentDescription = "QR Scanner") },
                    text = { Text(text = "Escanear QR") },
                    modifier = Modifier,
//                    .padding(bottom = 16.dp, end = 16.dp)
//                    .align(Alignment.BottomEnd),
//                    .offset(y = (-160).dp)
                )
            }
        }

        FloatingActionButton(
            onClick = {
                // onClickFloatingBottom()
                showFloatingButtons = !showFloatingButtons
            },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
                .align(Alignment.BottomEnd),
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
