package com.example.turistaapp.create_trip.ui.screens.create_trip

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.turistaapp.create_trip.ui.screens.navigation.components.BottomAppBarScreen
import com.example.turistaapp.create_trip.ui.screens.navigation.components.TopAppBarScreen
import com.example.turistaapp.create_trip.ui.screens.create_trip.components.TripForm
import com.example.turistaapp.ui.theme.TuristaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen() {

    Scaffold(
        topBar = {
            TopAppBarScreen()
        },
        bottomBar = {
            BottomAppBarScreen()
        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TripForm()
    }
}

@Preview
@Composable
private fun CreateTripScreenPrev() {
    TuristaAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CreateTripScreen()
        }
    }

}