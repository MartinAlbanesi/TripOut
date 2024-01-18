package com.example.turistaapp.home.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
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
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.home.ui.components.LottiePreview
import com.example.turistaapp.map.ui.components.NearbySearchView
import com.example.turistaapp.map.ui.components.TripDialog
import com.example.turistaapp.my_trips.ui.screens.components.MyTripsItem
import com.example.turistaapp.qr_code.domain.models.toDataQRModel
import com.google.gson.Gson


@Composable
fun ShakeDiscover(
    onClickShakeGame: () -> Unit
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClickShakeGame() }
    ) {
        LottiePreview(
            title = "",
            lottieRes = R.raw.world,
            isBackgroundColored = true,
            isBottomBrush = true,
            isClickable = false,
            modifierLottie = Modifier
                .fillMaxWidth()
                .offset(x = 150.dp),
        ) {}

        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0x46000000))
        ) {
            Text(
                text = stringResource(R.string.shake_n_discover),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.your_travel_guide) + "\n" + stringResource(R.string.discover_exciting),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        ElevatedButton(
            onClick = { onClickShakeGame() },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart),
        ) {
            Text(text = stringResource(R.string.pick_shake_trevel))
        }

    }

}

@SuppressLint("NewApi")
@Composable
fun HomeScreen(
    nearbyLocations: ResponseUiState,
    nearbyLocationSelect: LocationModel?,
    myTrips: List<TripModel>,
    locationSelect: String = "",
    isQRDialogOpen: Boolean = false,
    dataQRSelected: String = "",
    onIsQRDialogOpenChange: (Boolean) -> Unit,
    onDataQRSelectedChange: (String) -> Unit,
    onCreateTripDialog: (String) -> Unit,
    onCardSelection: (String) -> Unit,
    onClickFloatingBottom: () -> Unit,
    onClickShakeGame: () -> Unit,
    onQRButtonClick: () -> Unit,
    onTripClick: (Int) -> Unit,
    onDeleteTripButtonClick: (TripModel) -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    var showFloatingButtons by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
//                LottiePreview(
//                    title = stringResource(R.string.shake_n_discover),
//                    lottieRes = R.raw.world,
//                    isBackgroundColored = true,
//                    isBottomBrush = true,
//                    isClickable = true,
//                ) {
//                    onClickShakeGame()
//                }
                ShakeDiscover() {
                    onClickShakeGame()
                }
            }
            item {
                Column {
                    Text(
                        text = stringResource(R.string.discover_more_trips),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    )

                    when (nearbyLocations) {
                        is ResponseUiState.Success<*> -> {
                            Text(
                                text = stringResource(R.string.nearly, locationSelect),
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier
                                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                            )
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
                            LottiePreview(
                                title = stringResource(R.string.no_results_found),
                                lottieRes = R.raw.marker,
                            ) {}
                        }
                    }
                }
            }
            item {
                Text(
                    text = stringResource(R.string.my_trips),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineLarge,
                )
                if (myTrips.isEmpty()) {
                    LottiePreview(
                        title = stringResource(R.string.create_your_first_trip),
                        lottieRes = R.raw.map,
                        isBackgroundColored = true,
                        isTopBrush = true,
                        isClickable = true,
                    ) {
                        onClickFloatingBottom()
                    }
                }
            }
            items(myTrips) { trip ->
                MyTripsItem(
                    trip = trip,
                    selectedDataQR = dataQRSelected,
                    isDialogOpen = isQRDialogOpen,
                    onDeleteButtonClick = {
                        onDeleteTripButtonClick(it)
                    },
                    onDismissDialog = { onIsQRDialogOpenChange(false) },
                    onQRButtonClick = {
                        onIsQRDialogOpenChange(true)
                        onDataQRSelectedChange(Gson().toJson(trip.toDataQRModel()))
                    },
                    onMapButtonClick = {
                        onTripClick(it)
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                )
            }
        }

        AnimatedVisibility(
            visible = showFloatingButtons,
            enter = expandVertically(expandFrom = Alignment.Top) { 0 },
            exit = shrinkVertically(shrinkTowards = Alignment.Bottom) { 0 },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
                .offset(y = (-80).dp),
        ) {
            Column {
                ExtendedFloatingActionButton(
                    onClick = { onClickFloatingBottom() },
                    icon = { Icon(Icons.Default.Map, contentDescription = "Create Trip Form") },
                    text = { Text(text = stringResource(R.string.create_trip)) },
                    modifier = Modifier,
                )
                Spacer(modifier = Modifier.height(8.dp))
                ExtendedFloatingActionButton(
                    onClick = { onQRButtonClick() },
                    icon = {
                        Icon(
                            Icons.Default.QrCodeScanner,
                            contentDescription = "QR Code Scanner",
                        )
                    },
                    text = { Text(text = stringResource(R.string.scan_qr)) },
                    modifier = Modifier,
                )
            }
        }

        FloatingActionButton(
            onClick = {
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
