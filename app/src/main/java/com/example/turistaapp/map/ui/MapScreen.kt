package com.example.turistaapp.map.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.example.turistaapp.core.ui.components.TopAppBarScreen
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.domain.models.RouteModel
import com.example.turistaapp.qr_code.domain.models.toDataQRModel
import com.example.turistaapp.qr_code.ui.QRDialog
import com.example.turistaapp.setting.ui.TripDetails
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    locations: Pair<List<LocationModel>, List<LocationModel>>?,
    directionSelect: List<LatLng>,
    markerSelect: Boolean,
    lastLocation: LatLng?,
    routeModel: RouteModel?,
    isTutorialComplete: Boolean?,
    isQRDialogOpen: Boolean,
    dataQRSelected: String,
    onMarkerSelectChange: (Boolean) -> Unit,
    onIsQRDialogOpenChange: (Boolean) -> Unit,
    onDataQRSelectedChange: (String) -> Unit,
    onDismissQRDialog: () -> Unit,
    onClickArrowBack: () -> Unit,
    onMarkerSelected: (Int) -> Unit,
    onClickFinishTutorial: () -> Unit,
    onDeleteTripButtonClick: (TripModel) -> Unit,
) {
    val sheetPeekHeight by animateDpAsState(
        targetValue = if (markerSelect) 200.dp else 0.dp,
        label = "sheetPeekHeight",
        animationSpec = tween(1000),
    )

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false,
            ),
        )
    }

    var isLastLocation by remember {
        mutableStateOf(false)
    }

    val unlam = lastLocation ?: LatLng(-34.67112967722258, -58.56390981764954)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(unlam, 10f)
    }

    BottomSheetScaffold(
        sheetContent = {
            if (markerSelect) {
                TripDetails(
                    routeModel = routeModel,
                    onClickQR = {
                        onIsQRDialogOpenChange(true)
                        onDataQRSelectedChange(Gson().toJson(routeModel?.trip?.toDataQRModel()))
                    },
                    onDeleteTripButtonClick = {
                        onDeleteTripButtonClick(routeModel?.trip!!)
                        onMarkerSelectChange(false)
                    },
                )
            }
        },
        sheetPeekHeight = sheetPeekHeight,
    ) { paddingValues ->

        if (isTutorialComplete == null) {
            MapTutorial() {
                onClickFinishTutorial()
            }
        }

        Box(Modifier.fillMaxSize()) {
            MapView(
                mapUiSettings = mapUiSettings,
                cameraPositionState = cameraPositionState,
                locations = locations,
                directionSelect = directionSelect,
                markerSelect = markerSelect,
                isLastLocation = isLastLocation,
                lastLocation = lastLocation,
                onClickMarker = { onMarkerSelected(it) },
                onClickLocation = {
                    isLastLocation = !isLastLocation
                    if (isLastLocation) {
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(unlam, 14f)
                    }
                },
            )
            TopAppBarScreen(
                title = stringResource(R.string.my_destinations),
                isMarkerSelected = markerSelect,
                color = Color.Black,
                onClickNavigationBack = { onClickArrowBack() },
            )

            if (isQRDialogOpen) {
                QRDialog(
                    onDismiss = { onDismissQRDialog() },
                    data = dataQRSelected,
                )
            }
        }
    }
}
