package com.example.turistaapp.map.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import com.example.turistaapp.R
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
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    val permissionMessage = stringResource(R.string.no_permission_location)
    val snackBarAction = stringResource(R.string.activate)
    val gpsMessage = stringResource(R.string.gps_no_activated)
    val errorLocation = stringResource(R.string.error_location)

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
                                    message = permissionMessage,
                                    actionLabel = snackBarAction,
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
                                    message = gpsMessage,
                                    actionLabel = snackBarAction,
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
                            errorLocation,
                            Toast.LENGTH_SHORT,
                        ).show()
                    } else {
                        onClickLocation()
                    }
                },
                modifier = Modifier,
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "Add")
            }
        },
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
                        title = stringResource(R.string.my_location),
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                    )
                }
            }
        }
    }
}
