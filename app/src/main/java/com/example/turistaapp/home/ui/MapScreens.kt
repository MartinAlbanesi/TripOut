package com.example.turistaapp.home.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.turistaapp.R
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState

@Composable
fun MapScreen(
    mapUiSettings: MapUiSettings,
    cameraPositionState: CameraPositionState,
    locations: List<LocationModel> = emptyList(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState,
        ) {
            locations.forEach {location ->
//                Marker(
//                    state = MarkerState(
//                        position = LatLng(it.lat, it.lng),
//                    ),
//                    title = it.name,
//                    snippet = it.address,
//                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
//                )
                MarkerInfoWindow(
                    state = MarkerState(LatLng(location.lat, location.lng)),
                    icon = if(!location.isFinished)
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                        else
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                    onInfoWindowClick = {
                        Log.d("titi", location.photoUrl ?: "no photo url")
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                            )
                        ,
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            AsyncImage(
                                model = "location.photoUrl",
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(80.dp)
                                    .fillMaxWidth(),
                                placeholder = painterResource(id = R.drawable.placeholder)
                                )
                            //.........................Spacer
                            Spacer(modifier = Modifier.height(24.dp))
                            //.........................Text: title
                            Text(
                                text = "title",//location.tripName,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            //.........................Text : description
                            Text(
                                text = location.address ?: location.name,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            //.........................Spacer
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Ver Detalles")
                            }

                        }

                    }
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
            Text(
                text = "Mis Destinos",
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(9f),
                color = Color.Black,
                fontSize = 24.sp,
            )
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

@Composable
private fun IconForMap(
    painter: Int,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
) {
    IconButton(
        onClick = { onClickButton() },
        modifier = modifier.size(24.dp),
    ) {
        Icon(
            painter = painterResource(id = painter),
            contentDescription = contentDescription,
            tint = Color.Black,
        )
    }
}
