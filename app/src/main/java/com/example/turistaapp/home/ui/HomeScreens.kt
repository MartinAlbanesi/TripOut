package com.example.turistaapp.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.MapUiSettings

@Composable
fun HomeScreen(paddingValues: PaddingValues) {

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false
            )
        )
    }
//    LazyColumn(
//        Modifier
//            .fillMaxSize()
//    ) {
//        item {
//
            MapScreen(mapUiSettings, Modifier.padding(paddingValues))
//
//        }
//    }
}

