package com.example.turistaapp.map.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.map.ui.components.SheetContent
import com.example.turistaapp.map.ui.components.TripDialog
import com.example.turistaapp.main.ui.components.TopAppBarScreen
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    nearbyLocations: ResponseUiState,
    nearbyLocationSelect: LocationModel?,
    locations: Pair<List<LocationModel>, List<LocationModel>>?,
    directionSelect : List<LatLng>,
    markerSelect : Boolean,
    onClickArrowBack: () -> Unit,
    onMarkerSelected : (Int) -> Unit,
    onClickFloatingBottom: () -> Unit,
    onCreateTripDialog: (String) -> Unit,
    onCardSelection: (String) -> Unit,
) {

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
            ),
        )
    }

    val unlam = LatLng(-34.67112967722258, -58.56390981764954)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(unlam, 11f)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    BottomSheetScaffold(
//        topBar = {
//            TopAppBarScreen(
//                title = "Home",
//                iconsNavigation = null
//            )
//        },
        // Lo que va dentro del BottomSheet
        sheetContent = {
            when (nearbyLocations) {
                is ResponseUiState.Error -> {
                    Box(
                        modifier = Modifier
                            .size(200.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = nearbyLocations.message,
                        )
                    }
                }

                ResponseUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(100.dp),
                    )
                }

                is ResponseUiState.Success<*> -> {
                    SheetContent(
                        nearbyLocations = nearbyLocations.values as List<LocationModel>,
                        onClickCard = {
                            showDialog = true
                            onCardSelection(it)
                        },
                    )
                }
            }
        },
    ) { paddingValues ->

        Box(Modifier.fillMaxSize()) {
            MapScreen(
                mapUiSettings = mapUiSettings,
                cameraPositionState = cameraPositionState,
                locations = locations,
                directionSelect = directionSelect,
                markerSelect = markerSelect,
//                onInfoWindowClose = {
//                    onClickArrowBack()
//                },
                onClickMarker = { onMarkerSelected(it) }
            )
            TopAppBarScreen(
                title = "Mis Destinos",
                isMarkerSelected = markerSelect,
                color = Color.Black,
                onClickNavigationBack = {onClickArrowBack()}
            )
            FloatingActionButton(
                onClick = { onClickFloatingBottom() },
                modifier = Modifier
                    .padding(bottom = paddingValues.calculateBottomPadding() + 16.dp, end = 16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
            if (showDialog) {
                if (nearbyLocationSelect != null) {
                    TripDialog(
                        name = nearbyLocationSelect.name,
                        photoUrl = nearbyLocationSelect.photoUrl,
                        rating = nearbyLocationSelect.rating,
                        userRating = nearbyLocationSelect.userRating,
                        address = nearbyLocationSelect.address,
                        onDismiss = { showDialog = false },
                        onConfirm = {
                            showDialog = false
                            onCreateTripDialog(it)
                        },
                    )
                }
            }
        }
    }
}
