package com.example.turistaapp.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline

@Composable
fun MapScreen(
    mapUiSettings: MapUiSettings,
    cameraPositionState: CameraPositionState,
    locations: Pair<List<LocationModel>, List<LocationModel>>,
    directionSelect: List<LatLng>,
    markerSelect : Boolean,
//    onInfoWindowClose: () -> Unit,
    onClickMarker: (Int) -> Unit,
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
            if(markerSelect)Polyline(points = directionSelect)

            locations.second.forEach {
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
//                    onInfoWindowClose = {
//                        onInfoWindowClose()
//                    }
                )

            }
            if(markerSelect){
                locations.first.forEach {
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
        }

        // Barra Superior
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0x1A000000))
                .align(Alignment.TopCenter),
        ) {
//            Spacer(modifier = Modifier.weight(2f))
//            Text(
//                text = "Mis Destinos",
//                textAlign = TextAlign.Center,
//                modifier = Modifier.weight(9f),
//                color = Color.Black,
//                fontSize = 24.sp,
//            )
//            IconForMap(
//                painter = R.drawable.tune,
//                modifier = Modifier
//                    .align(CenterVertically)
//                    .weight(1f),
//            ) {}
//            IconForMap(
//                painter = R.drawable.fullscreen,
//                modifier = Modifier
//                    .align(CenterVertically)
//                    .weight(1f),
//            ) {}
        }
    }
}

//@Composable
//private fun IconForMap(
//    painter: Int,
//    contentDescription: String = "",
//    modifier: Modifier = Modifier,
//    onClickButton: () -> Unit,
//) {
//    IconButton(
//        onClick = { onClickButton() },
//        modifier = modifier.size(24.dp),
//    ) {
//        Icon(
//            painter = painterResource(id = painter),
//            contentDescription = contentDescription,
//            tint = Color.Black,
//        )
//    }
//}
