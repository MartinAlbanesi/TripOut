package com.example.turistaapp.map.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.utils.isAccessCoarseLocationPermissionsGranted
import com.example.turistaapp.home.utils.isGPSEnable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapView(
    mapUiSettings: MapUiSettings,
    cameraPositionState: CameraPositionState,
    locations: Pair<List<LocationModel>, List<LocationModel>>?,
    directionSelect: List<LatLng>,
    markerSelect: Boolean,
    lastLocation: LatLng?,
    isLastLocation: Boolean,
    onClickMarker: (Int) -> Unit,
    onClickLocation: () -> Unit,
) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val coarseLocation = rememberPermissionState(
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (!isAccessCoarseLocationPermissionsGranted(context)) {
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "No tienes permisos para acceder a la ubicación",
                                    actionLabel = "Activar",
                                    duration = SnackbarDuration.Indefinite,
                                    withDismissAction = true,
                                )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
//                                    onClickPermission()
                                    coarseLocation.launchPermissionRequest()
                                }
                                SnackbarResult.Dismissed -> {
                                    /* Handle snackbar dismissed */
                                }
                            }
                        }
                    } else if (!isGPSEnable(context)) {
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = "No tienes el GPS activado",
                                    actionLabel = "Activar",
                                    duration = SnackbarDuration.Indefinite,
                                    withDismissAction = true,
                                )
                            when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                                }
                                SnackbarResult.Dismissed -> {
                                    /* Handle snackbar dismissed */
                                }
                            }
                        }
                    } else if (lastLocation == null) {
                        Toast.makeText(
                            context,
                            "Ocurrio un error al obtener tu ubicación",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        onClickLocation()
                    }
                },
                modifier = Modifier
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "Add")
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                uiSettings = mapUiSettings,
                cameraPositionState = cameraPositionState,
            ) {
                if (markerSelect) Polyline(points = directionSelect)

                locations?.second?.forEach {
                    Marker(
                        state = MarkerState(
                            position = LatLng(it.lat, it.lng),
                        ),
                        title = it.tripName,
                        snippet = it.name,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                        onClick = { _ ->
                            //TODO: Abrir pantalla de detalles
                            onClickMarker(it.tripId)
                            false
                        },
                    )

                }
                if (markerSelect) {
                    locations?.first?.forEach {
                        Marker(
                            state = MarkerState(
                                position = LatLng(it.lat, it.lng),
                            ),
                            title = it.tripName,
                            snippet = it.name,
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                        )
                    }
                }
                if (lastLocation != null && isLastLocation) {
                    Marker(
                        state = MarkerState(
                            position = lastLocation,
                        ),
                        title = "Mi ubicación",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    )
                }
            }
        }
    }
}
