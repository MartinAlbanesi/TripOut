package com.example.turistaapp.my_trips.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.turistaapp.my_trips.ui.screens.components.TripItem
import com.example.turistaapp.my_trips.ui.viewmodels.MyTripsViewModel

@Composable
fun MyTripsList(
    myTripsViewModel: MyTripsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true){
        myTripsViewModel.getTrips()
    }

    val trips by myTripsViewModel.trips.observeAsState(emptyList())

    LazyColumn {
        items(trips.size) { index ->
            TripItem(trips[index])
        }
    }
}