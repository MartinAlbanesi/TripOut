package com.example.turistaapp.map.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.utils.isAccessCoarseLocationPermissionsGranted
import com.example.turistaapp.home.utils.isGPSEnable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline

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

        FloatingActionButton(
            onClick = {
                if (!isAccessCoarseLocationPermissionsGranted(context)) {
                    Toast.makeText(
                        context,
                        "No hay Permisos de Ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!isGPSEnable(context)) {
                    Toast.makeText(context, "El GPS no está activado", Toast.LENGTH_SHORT).show()
                } else if (lastLocation == null) {
                    Toast.makeText(
                        context,
                        "No se puede acceder a la ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    onClickLocation()
                }
            },
            modifier = Modifier
                .padding(bottom = 16.dp, end = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.MyLocation, contentDescription = "Add")
        }
    }
}
