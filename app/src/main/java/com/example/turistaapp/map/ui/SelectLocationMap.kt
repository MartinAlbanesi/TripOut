package com.example.turistaapp.map.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import com.example.turistaapp.create_trip.ui.screens.components.PlaceAutocompleteField
import com.example.turistaapp.home.ui.ShakeViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun SelectLocationMap(
    shakeViewModel: ShakeViewModel = hiltViewModel(),
    type: String = "geocode",
    onConfirmLocation: (String) -> Unit,
) {

    val mapProperties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = true))
    }

    val originPredictions by shakeViewModel.originPredictions.observeAsState(emptyList())

    val selectedLocations by shakeViewModel.selectedLocation.collectAsStateWithLifecycle()

    val focusRequest = remember { FocusRequester() }

    var value by remember { mutableStateOf("") }

    var isMenuVisible by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState {}

    LaunchedEffect(selectedLocations) {
        selectedLocations?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                it.getLatLng(), 14f
            )
        }
    }

    ConstraintLayout(Modifier.fillMaxSize()) {

        val (topBar, mapView, destinationList, buttonConfirm) = createRefs()

        val bottomGuide = createGuidelineFromBottom(0.03f)

        ShowMapScreen(
            modifier = Modifier.constrainAs(mapView) {
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            mapProperties,
            cameraPositionState
        )
        MapTopBar(
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(8.dp),
            modifierMenuList = Modifier
                .constrainAs(destinationList) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 8.dp, end = 8.dp),
            value,
            focusRequest,
            isMenuVisible,
            originPredictions,
            onValueChange = { value = it },
            onSearchOriginPlaces = { shakeViewModel.searchOriginPlaces(it, type) },
            onClickSelectedLocation = {
                shakeViewModel.clickSelectedLocation(it)
            },
            isMenuVisibleChange = { isMenuVisible = it },
        )

        Button(
            onClick = {
                onConfirmLocation(selectedLocations?.name ?: "")
            },
            modifier = Modifier.constrainAs(buttonConfirm) {
                bottom.linkTo(bottomGuide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
                .fillMaxWidth(0.9f)
//                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Confirm")
        }
    }

}

@Composable
private fun MapTopBar(
    modifier: Modifier = Modifier,
    modifierMenuList: Modifier = Modifier,
    value: String,
    focusRequest: FocusRequester,
    isMenuVisible: Boolean,
    originPredictions: List<PlaceAutocompletePredictionModel>,
    onValueChange: (String) -> Unit,
    onSearchOriginPlaces: (String) -> Unit,
    onClickSelectedLocation: (String) -> Unit,
    isMenuVisibleChange: (Boolean) -> Unit,
) {
    PlaceAutocompleteField(
        label = "Search",
        query = value,
        onQueryChange = {
            onValueChange(it)
            onSearchOriginPlaces(value)
        },
        isDropdownVisible = isMenuVisible,
        onDropdownVisibilityChange = {
            isMenuVisibleChange(it)
        },
        predictions = originPredictions,
        focusRequester = focusRequest,
        imeAction = ImeAction.Done,
        onClearField = {
            onValueChange("")
        },
        onItemClick = {
            onValueChange(it.description ?: "")
            onClickSelectedLocation(it.placeId)
            isMenuVisibleChange(false)
            focusRequest.freeFocus()
        },
        leadingIcon = Icons.Default.ArrowBack,
        shape = CircleShape,
        modifier = modifier,
        modifierMenuList = modifierMenuList,
    )
}

@Composable
fun ShowMapScreen(
    modifier: Modifier = Modifier,
    mapProperties: MapProperties,
    cameraPositionState: CameraPositionState
) {

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties
    ) {
        Marker(
            state = MarkerState(position = cameraPositionState.position.target),
        )
    }
}

//@PreviewScreenSizes
//@Preview
//@PreviewLightDark
//@Composable
//private fun SelectLocationMapPreview() {
//    TuristaAppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background,
//        ) {
//            SelectLocationMap(){
//
//            }
//        }
//    }
//}