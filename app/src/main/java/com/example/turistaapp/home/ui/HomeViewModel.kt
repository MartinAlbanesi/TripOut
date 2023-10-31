package com.example.turistaapp.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.home.utils.getLocationString
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getNearbyLocationsUseCase: GetNearbyLocationsUseCase,
    private val getRandomLocationFromDB: GetRandomLocationFromDB,
) : ViewModel(){

    private val _nearbyLocationsApi = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    private val _nearbyLocationSelect = MutableStateFlow<LocationModel?>(null)
    val nearbyLocationSelect = _nearbyLocationSelect.asStateFlow()

    private fun getRandomLocation() {
        viewModelScope.launch(dispatcher) {
            if (getRandomLocationFromDB() != null) {
                setNearbyLocations(getRandomLocationFromDB()!!.lat, getRandomLocationFromDB()!!.lng)
            } else {
                setNearbyLocations(-34.67113975510375, -58.56181551536259)
            }
        }
    }

    init {
        getRandomLocation()
    }


    fun setNearbyLocations(lat: Double, lng: Double) {
        viewModelScope.launch(dispatcher) {
            try {
                val nearbyLocations = getNearbyLocationsUseCase(getLocationString(lat, lng))

                if (nearbyLocations.isNullOrEmpty()) {
                    _nearbyLocationsApi.emit(ResponseUiState.Error("No se encontraron lugares cercanos"))
                } else {
                    _nearbyLocationsApi.emit(ResponseUiState.Success(nearbyLocations))
                }
            } catch (e: Exception) {
                _nearbyLocationsApi.emit(ResponseUiState.Error(e.message.toString()))
            }
        }
    }

    fun setNearbyLocationSelect(nearbyLocationName: String) {
        val getNearbyLocation = getNearbyLocationByName(nearbyLocationName)
        _nearbyLocationSelect.value = getNearbyLocation
    }

    private fun getNearbyLocationByName(name: String): LocationModel? {
        val nearbyLocation =
            (nearbyLocations.value as ResponseUiState.Success<List<LocationModel>>).values
        return nearbyLocation.find { it.name == name }
    }

}