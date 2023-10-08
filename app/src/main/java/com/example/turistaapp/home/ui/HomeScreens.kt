package com.example.turistaapp.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.turistaapp.home.domain.models.NearbyLocation
import com.example.turistaapp.home.ui.components.SheetContent
import com.example.turistaapp.home.ui.components.TripDialog
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    nearbyLocations: List<NearbyLocation>,
    nearbyLocationSelect: NearbyLocation?,
    onCardSelection: (String) -> Unit
) {
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false
            )
        )
    }

    val unlam = LatLng(-34.67112967722258, -58.56390981764954)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(unlam, 14f)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    val paddingFromScaffold by remember {
        mutableStateOf(paddingValues.calculateBottomPadding() * 1.5f)
    }

    BottomSheetScaffold(
        sheetPeekHeight = paddingFromScaffold,

        //Lo que va dentro del BottomSheet
        sheetContent = {
            SheetContent(
                paddingValues = paddingValues,
                nearbyLocations = nearbyLocations,
                onClickCard = {
                    showDialog = true
                    onCardSelection(it)
                }
            )
        },
    ) {
        MapScreen(
            mapUiSettings,
            cameraPositionState,
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
        if (showDialog) {
            if (nearbyLocationSelect != null) {
                TripDialog(
                    name = nearbyLocationSelect.name,
                    photoUrl = nearbyLocationSelect.photoUrl,
                    onDismiss = { showDialog = false },
                    onConfirm = { showDialog = false }
                )
            }
        }

    }
}



