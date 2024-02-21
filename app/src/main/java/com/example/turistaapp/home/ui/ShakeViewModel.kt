package com.example.turistaapp.home.ui

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
    private val sensorManager: SensorManager,
) : ViewModel() {

    private val _selectedLocations = MutableStateFlow<List<LocationModel>>(emptyList())
    val selectedLocations = _selectedLocations.asStateFlow()

    private val _originPredictions = MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val originPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _originPredictions

    // SelectedLocationMap.kt ---------------------------------------------------------
    private val _selectedLocation = MutableStateFlow<LocationModel?>(null)
    val selectedLocation = _selectedLocation.asStateFlow()

    fun clickSelectedLocation(placeId: String)  {
        viewModelScope.launch {
            _selectedLocation.value = getPlaceDetailsUseCase(placeId)!!
        }
    }

//    fun clickSelectedLocation(placeId: String) {
//        viewModelScope.launch {
//            _selectedLocation.value = getPlaceDetailsUseCase(placeId)!!
//        }
//    }
    // SelectedLocationMap.kt ---------------------------------------------------------

    fun onClickSelectedLocations(placeId: String) {
        viewModelScope.launch {
            _selectedLocations.value += getPlaceDetailsUseCase(placeId)!!
        }
    }

    fun onClickDeletedLocation(location: LocationModel) {
        viewModelScope.launch {
            _selectedLocations.value -= location
        }
    }

    fun searchOriginPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query, "establishment")
            _originPredictions.value = newPredictions
        }
    }

    fun getSensorAcceleration(): Sensor? {
        return sensor
    }

    fun getSensorManager(): SensorManager {
        return sensorManager
    }
}
