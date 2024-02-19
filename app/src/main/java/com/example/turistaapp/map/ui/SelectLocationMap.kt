package com.example.turistaapp.map.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.compose.TuristaAppTheme
import com.example.turistaapp.core.ui.NavHostScreen
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
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
    latLng: LatLng? = null
) {

    var showOptions by remember {
        mutableStateOf(true)
    }

    val optionsList = listOf(
        SelectedItem("Choose on Map", Icons.Filled.Map),
        SelectedItem("Your Location", Icons.Filled.MyLocation)
    )

    Scaffold {
        Column() {
            MapTopBar()

            Spacer(modifier = Modifier.height(8.dp))

            if (!showOptions) {
                ShowMapScreen(latLng )
                BackHandler {
                    showOptions = true
                }
            } else {
                Column(modifier = Modifier.padding(8.dp)) {
                    optionsList.forEach { item ->
                        ItemSelectionMap(
                            text = item.text,
                            icon = item.icon
                        ) {
                            showOptions = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemSelectionMap(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(text = text) },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        modifier = Modifier.clickable { onClick() }
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun MapTopBar() {
    Row(modifier = Modifier.height(64.dp)) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Search") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(7f)
                .padding(end = 8.dp)
        )

    }
}

@Composable
fun ShowMapScreen(
    latLng: LatLng? = null
) {
    val mapProperties = MapProperties(isMyLocationEnabled = true)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng ?: LatLng(0.0, 0.0), 10f)
    }


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