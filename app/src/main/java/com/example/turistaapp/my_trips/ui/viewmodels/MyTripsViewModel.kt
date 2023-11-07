package com.example.turistaapp.my_trips.ui.viewmodels

import android.app.AlertDialog
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import javax.inject.Inject


@HiltViewModel
class MyTripsViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getTripsUseCase: GetTripsUseCase,
) : ViewModel() {
    init {
        getTrips()
    }

    private val _trips = MutableStateFlow<List<TripModel>>(emptyList())
    val trips = _trips.asStateFlow()

    fun getTrips() {
        viewModelScope.launch(dispatcher) {
            getTripsUseCase()
                .collect { tripList ->
                    _trips.value = tripList
                }
        }
    }
}
