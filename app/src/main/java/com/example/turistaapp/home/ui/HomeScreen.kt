package com.example.turistaapp.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.ArrowOutward
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.turistaapp.R
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.ui.components.NearbySearchView
import com.example.turistaapp.map.ui.components.TripDialog
import com.example.turistaapp.my_trips.ui.screens.components.TripItem
import com.example.turistaapp.qr_code.domain.models.toDataQRModel
import com.google.gson.Gson

@Composable
fun LottiePreview(
    title: String = "",
    res: Int,
    isBackgroundColored: Boolean = false,
    isClickable: Boolean = false,
    isBottomBrush: Boolean = false,
    isTopBrush: Boolean = false,
    onClickAnimation: () -> Unit,
) {
    val lottie = rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    var background by remember {
        mutableStateOf(Color.Transparent)
    }

    var textBackground by remember {
        mutableStateOf(Color.Transparent)
    }

    var bottomBrush by remember {
        mutableStateOf(Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent)))
    }

    var topBrush by remember {
        mutableStateOf(Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent)))
    }

    if (isBackgroundColored) {
        background = MaterialTheme.colorScheme.onPrimary
    }

    if (isBottomBrush) {
        bottomBrush =
            Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.background))
    }

    if (isTopBrush) {
        topBrush =
            Brush.verticalGradient(listOf(MaterialTheme.colorScheme.background, Color.Transparent))
    }

    val boxClickable: Modifier by remember {
        mutableStateOf(
            if (isClickable) {
                Modifier.clickable { onClickAnimation() }
            } else {
                Modifier
            },
        )
    }

//    boxClickable = if (isClickable) Modifier.clickable { onClickAnimation() } else { Modifier }

    Box(
        modifier = boxClickable
            .fillMaxWidth()
            .height(200.dp)
//            .clickable {
//                onClickAnimation()
//            }
            .background(background),
        contentAlignment = Alignment.BottomStart,
    ) {
        Row(
            modifier = Modifier
                .background(brush = topBrush)
                .height(100.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
        ) {
            Spacer(modifier = Modifier.size(8.dp))
//
        }
        LottieAnimation(
            composition = lottie.value,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        )
        if (title.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .background(brush = bottomBrush),
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp, vertical = 12.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    imageVector = Icons.Default.ArrowOutward,
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(40.dp),
                )
            }
        }
    }
}

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
    onTripClick: (Int) -> Unit,
    onDeleteTripButtonClick: (TripModel) -> Unit,
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

    var dataQRSelected by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                LottiePreview(
                    title = "Shake'n Discover",
                    res = R.raw.world,
                    isBackgroundColored = true,
                    isBottomBrush = true,
                    isClickable = true,
                ) {
                    onClickShakeGame()
                }
            }
            item {
                Column {
                    Text(
                        text = "Descubre más viajes",
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    )

                    when (nearbyLocations) {
                        is ResponseUiState.Success<*> -> {
                            Text(
                                text = "Cerca de $locationSelect",
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
                                title = "No se encontraron resultados",
                                res = R.raw.marker,
                            ) {}
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
                if (myTrips.isEmpty()) {
                    LottiePreview(
                        title = "¡Crea tu primer viaje!",
                        res = R.raw.map,
                        isBackgroundColored = true,
                        isTopBrush = true,
                        isClickable = true,
                    ) {
                        onClickFloatingBottom()
                    }
                }
            }
            items(myTrips) { trip ->
                TripItem(
                    trip = trip,
                    selectedDataQR = dataQRSelected,
                    isDialogOpen = isQRDialogOpen,
                    onDeleteButtonClick = {
                        onDeleteTripButtonClick(it)
                    },
                    onDismissDialog = { isQRDialogOpen = false },
                    onQRButtonClick = {
                        isQRDialogOpen = true
                        dataQRSelected = Gson().toJson(trip.toDataQRModel())
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
                    icon = { Icon(Icons.Default.Map, contentDescription = "Create Trip Form") },
                    text = { Text(text = "Crear Viaje") },
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
                    text = { Text(text = "Escanear QR") },
                    modifier = Modifier,
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
