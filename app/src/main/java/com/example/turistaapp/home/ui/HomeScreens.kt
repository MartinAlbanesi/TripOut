package com.example.turistaapp.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.turistaapp.R
import com.google.maps.android.compose.MapUiSettings

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

    val paddingFromScaffold by remember {
        mutableStateOf(paddingValues.calculateBottomPadding() * 1.5f)
    }

    BottomSheetScaffold(
        sheetPeekHeight = paddingFromScaffold,

        //Lo que va dentro del BottomSheet
        sheetContent = {
            Box(Modifier.padding(paddingValues)) {
                Card() {
                Text(text = "Descubre más viajes.", modifier = Modifier.padding(4.dp))
                    LazyRow(modifier = Modifier.padding(4.dp)) {
                        items(10) {
                            TripItem(
                                image = painterResource(id = R.drawable.ic_launcher_background),
                                modifier = Modifier
                                    .size(150.dp, 250.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        },
    ) {
        MapScreen(
            mapUiSettings,
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
fun <T> TripItem(image: T, modifier: Modifier = Modifier) {
    when (image) {
        is ImageVector -> {
            Image(
                image,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
        }

        is Painter -> {
            Image(
                image,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
        }

        is ImageBitmap -> {
            Image(
                image,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
        }
    }


}

