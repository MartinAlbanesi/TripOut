package com.example.turistaapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.turistaapp.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(paddingValues: PaddingValues) {

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
            Card(Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
                Text(text = "Descubre más viajes.", modifier = Modifier.padding(4.dp))
                LazyRow(modifier = Modifier.padding(4.dp)) {
                    items(10) {
                        TripItem(
                            image = painterResource(id = R.drawable.ic_launcher_background),
                            modifier = Modifier
                                .clickable {
                                    showDialog = true
                                }
                                .size(150.dp, 250.dp)
                                .padding(4.dp),
                        )
                    }
                }
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

/**
 * TripItem es un item que se muestra en el BottomSheet
 * @param image: Painter
 * @param modifier: Modifier
 */

@Composable
fun /*<T>*/ TripItem(
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Image(
        image,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )

//    when (image) {
//        is ImageVector -> {
//            Image(
//                image,
//                contentDescription = null,
//                modifier = modifier.clickable { onClickItem },
//                contentScale = ContentScale.FillBounds
//            )
//        }
//
//        is Painter -> {
//            Image(
//                image,
//                contentDescription = null,
//                modifier = modifier.clickable { onClickItem },
//                contentScale = ContentScale.FillBounds
//            )
//        }
//
//        is ImageBitmap -> {
//            Image(
//                image,
//                contentDescription = null,
//                modifier = modifier.clickable { onClickItem },
//                contentScale = ContentScale.FillBounds
//            )
//        }
//    }


}

@Composable
fun TripDialog(
    title: String,
    km: String,
    image: Painter,
    isShow: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isShow) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {
                        Text(text = title)
                        Text(text = km)
                        Image(
                            image, contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(5f)
                        )
                        Button(
                            onClick = { onConfirm() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(CenterHorizontally)
                        ) {
                            Text(text = "Confirmar Viaje")
                        }
                    }
                }

                Icon(
                    Icons.Outlined.Cancel,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { onDismiss() }
                )

            }
        }
    }
}