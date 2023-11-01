package com.example.turistaapp.map.ui

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.turistaapp.core.ui.components.TopAppBarScreen
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    locations: Pair<List<LocationModel>, List<LocationModel>>?,
    directionSelect : List<LatLng>,
    markerSelect : Boolean,
    lastLocation: LatLng?,
    onClickArrowBack: () -> Unit,
    onMarkerSelected : (Int) -> Unit,
) {

    val coarseLocationPermission = rememberPermissionState(
        permission = Manifest.permission.ACCESS_COARSE_LOCATION
    )

    LaunchedEffect(true){
        coarseLocationPermission.launchPermissionRequest()
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
            ),
        )
    }

    val unlam = lastLocation ?: LatLng(-34.67112967722258, -58.56390981764954)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(unlam, 10f)
    }

    BottomSheetScaffold(
        sheetContent = {
            //TODO : Agregar BottomSheet Content
        },
    ) { paddingValues ->

        Box(Modifier.fillMaxSize()) {
            MapView(
                mapUiSettings = mapUiSettings,
                cameraPositionState = cameraPositionState,
                locations = locations,
                directionSelect = directionSelect,
                markerSelect = markerSelect,
                onClickMarker = { onMarkerSelected(it) }
            )
            TopAppBarScreen(
                title = "Mis Destinos",
                isMarkerSelected = markerSelect,
                color = Color.Black,
                onClickNavigationBack = {onClickArrowBack()}
            )
        }
    }
}
