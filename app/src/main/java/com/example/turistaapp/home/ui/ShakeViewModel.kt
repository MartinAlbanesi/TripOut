package com.example.turistaapp.home.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import com.example.turistaapp.create_trip.domain.GetPlaceDetailsUseCase
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShakeViewModel @Inject constructor(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase,
    private val sensor: Sensor?,
    private val sensorManager: SensorManager
): ViewModel() {

    private val _selectedLocations = MutableStateFlow<List<LocationModel>>(emptyList())
    val selectedLocations = _selectedLocations.asStateFlow()

    private val _originPredictions =
        MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val originPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _originPredictions

    fun onClickSelectedLocation(placeId: String){
        viewModelScope.launch {
            _selectedLocations.value += getPlaceDetailsUseCase(placeId)!!
        }
    }

    fun searchOriginPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query)
            _originPredictions.value = newPredictions
        }
    }

    fun getSensorAcceleration(): Sensor?{
        return sensor
    }

    fun getSensorManager(): SensorManager {
        return sensorManager
    }
}