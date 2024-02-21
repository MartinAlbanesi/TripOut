package com.example.turistaapp.map.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.TuristaAppTheme
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import com.example.turistaapp.create_trip.ui.screens.components.PlaceAutocompleteField
import com.example.turistaapp.home.ui.ShakeViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

private data class SelectedItem(
    val text: String,
    val icon: ImageVector
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectLocationMap(
    shakeViewModel: ShakeViewModel = hiltViewModel(),
) {

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(isMyLocationEnabled = true)
        )
    }

    val originPredictions by shakeViewModel.originPredictions.observeAsState(emptyList())

    val selectedLocations by shakeViewModel.selectedLocation.collectAsStateWithLifecycle()

    val focusRequest = remember { FocusRequester() }

    var value by remember { mutableStateOf("") }

    var isMenuVisible by remember { mutableStateOf(false) }

    var showOptions by remember { mutableStateOf(true) }

    val cameraPositionState = rememberCameraPositionState {}

//    val optionsList = listOf(
//        SelectedItem("Choose on Map", Icons.Filled.Map),
//        SelectedItem("Your Location", Icons.Filled.MyLocation)
//    )

    LaunchedEffect(selectedLocations){
        selectedLocations?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                it.getLatLng(), 14f
            )
        }
    }

    Scaffold {
        Column() {
            MapTopBar(
                value,
                focusRequest,
                isMenuVisible,
                originPredictions,
                onValueChange = { value = it },
                onSearchOriginPlaces = { shakeViewModel.searchOriginPlaces(it) },
                onClickSelectedLocation = {
                    shakeViewModel.clickSelectedLocation(it)
                },
                isMenuVisibleChange = { isMenuVisible = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ShowMapScreen(mapProperties, cameraPositionState)
            BackHandler {
                showOptions = true
            }

//            if (!showOptions) {
//                ShowMapScreen(mapProperties,cameraPositionState)
//                BackHandler {
//                    showOptions = true
//                }
//            } else {
//                Column(modifier = Modifier.padding(8.dp)) {
//                    optionsList.forEach { item ->
//                        ItemSelectionMap(
//                            text = item.text,
//                            icon = item.icon
//                        ) {
//                            showOptions = false
//                        }
//                    }
//                }
//            }
        }
    }
}

@Composable
private fun MapTopBar(
    value: String,
    focusRequest: FocusRequester,
    isMenuVisible: Boolean,
    originPredictions: List<PlaceAutocompletePredictionModel>,
    onValueChange: (String) -> Unit,
    onSearchOriginPlaces: (String) -> Unit,
    onClickSelectedLocation: (String) -> Unit,
    isMenuVisibleChange: (Boolean) -> Unit,
) {
//    Row(modifier = Modifier.height(800.dp)) {
//        IconButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .fillMaxHeight()
//                .weight(1f)
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = "Back"
//            )
//        }
//        OutlinedTextField(
//            value = "",
//            onValueChange = {},
//            label = { Text(text = "Search") },
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Search"
//                )
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(7f)
//                .padding(end = 8.dp)
//        )
    PlaceAutocompleteField(
        label = "Search",
        query = value,
        onQueryChange = {
//                value = it
//                shakeViewModel.searchOriginPlaces(value)
            onValueChange(it)
            onSearchOriginPlaces(value)
        },
        isDropdownVisible = isMenuVisible,
        onDropdownVisibilityChange = {
//                isMenuVisible = it
            isMenuVisibleChange(it)
        },
        predictions = originPredictions,
        focusRequester = focusRequest,
        imeAction = ImeAction.Done,
        onClearField = {
//                value = ""
            onValueChange("")
        },
        onItemClick = {
//                value = it.description ?: ""
            onValueChange(it.description ?: "")
//                shakeViewModel.onClickSelectedLocation(it.placeId)
            onClickSelectedLocation(it.placeId)
//                value = ""
//                onValueChange("")
//                isMenuVisible = false
            isMenuVisibleChange(false)
            focusRequest.freeFocus()
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Map, contentDescription = null)
        },
    )

//    }
}

//@Composable
//private fun ItemSelectionMap(
//    text: String,
//    icon: ImageVector,
//    onClick: () -> Unit
//) {
//    ListItem(
//        headlineContent = { Text(text = text) },
//        leadingContent = {
//            Icon(
//                imageVector = icon,
//                contentDescription = null
//            )
//        },
//        modifier = Modifier.clickable { onClick() }
//    )
//    Spacer(modifier = Modifier.height(8.dp))
//}

@Composable
fun ShowMapScreen(
    mapProperties: MapProperties,
    cameraPositionState: CameraPositionState
) {

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties
    ) {
        Marker(
            state = MarkerState(position = cameraPositionState.position.target),
        )
    }
}

@PreviewScreenSizes
@Preview
@PreviewLightDark
@Composable
private fun SelectLocationMapPreview() {
    TuristaAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            SelectLocationMap()
        }
    }
}