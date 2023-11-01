package com.example.turistaapp.my_trips.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.LaunchedEffect

@HiltViewModel
class MyTripsViewModel @Inject constructor(
    private val getTripsUseCase: GetTripsUseCase,
) : ViewModel() {
    private var _trips = MutableLiveData<List<TripModel>>()
    val trips: LiveData<List<TripModel>> = _trips

    suspend fun getTrips() {
        viewModelScope.launch {
            getTripsUseCase()
                .collect { tripList ->
                    _trips.value = tripList
                }
        }
    }
}
