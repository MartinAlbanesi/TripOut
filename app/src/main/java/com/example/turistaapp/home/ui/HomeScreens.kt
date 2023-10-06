package com.example.turistaapp.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.home.domain.models.NearbyLocation
import com.example.turistaapp.home.ui.components.TripDialog
import com.example.turistaapp.home.ui.components.TripItem
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    nearbyLocations: List<NearbyLocation>
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
//            SheetContent(
//                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
//                nearbyLocations = nearbyLocations
//            )
            Card(Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {

                Text(text = "Descubre más viajes.", modifier = Modifier.padding(4.dp))

//                LazyRow(modifier = Modifier.padding(4.dp)) {
//                    items(nearbyLocations) {
//                        TripItem(
//                            name = it.name,
//                            image = "https://upload.wikimedia.org/wikipedia/commons/1/14/WEA_Music_Logo.png",
//                            modifier = Modifier
//                                .clickable {
//                                    showDialog = true
//                                }
//                                .size(150.dp, 250.dp)
//                                .padding(4.dp),
//                        )
//                    }
//                }
            }
        },
    ) {
        MapScreen(
            mapUiSettings,
            cameraPositionState,
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
        if(showDialog){
            TripDialog(
                title = "Titi",
                km = "11.9Km",
                image = painterResource(id = R.drawable.ic_launcher_background),
                isShow = showDialog,
                onDismiss = { showDialog = false },
                onConfirm = { showDialog = false }
            )
        }
    }
}



