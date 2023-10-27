package com.example.turistaapp.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.core.utils.getLocationString
import com.example.turistaapp.create_trip.domain.GetTripsUseCase
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import com.example.turistaapp.home.domain.GetRouteModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getNearbyLocationsUseCase: GetNearbyLocationsUseCase,
    private val getRandomLocationFromDB: GetRandomLocationFromDB,
    private val getGetTripsUseCase: GetTripsUseCase,
    private val getRouteModel: GetRouteModel
) : ViewModel() {

    private val _nearbyLocationsApi = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    private val _nearbyLocationSelect = MutableStateFlow<LocationModel?>(null)
    val nearbyLocationSelect = _nearbyLocationSelect.asStateFlow()

    private val _destinationLocations =
        MutableStateFlow<Pair<List<LocationModel>, List<LocationModel>>?>(null)
    val destinationLocations = _destinationLocations.asStateFlow()

    private val _polyLinesPoints = MutableStateFlow<List<LatLng>>(emptyList())
    val polyLinesPoints = _polyLinesPoints.asStateFlow()

    private val _markerSelect = MutableStateFlow(false)
    val markerSelect = _markerSelect.asStateFlow()

    init {
        getFlowLocationFromDB()
        getRandomLocation()
    }

    fun getTripById(id: Int) {
//        if (!markerSelect.value) {
            viewModelScope.launch(dispatcher) {
                try {
                    val selectTrip =getGetTripsUseCase().first().first { it.tripId == id }
//                        getGetTripsUseCase().map { it.filter { trip -> trip.tripId == id } }.first()
//                            .first()
                    val selectRoute = getRouteModel(
                        origin = selectTrip.getLatLngOrigin(),
                        destination = selectTrip.getLatLngDestination(),
                        transport = selectTrip.transport,
                        trip = selectTrip
                    )
                    decoPoints(selectRoute!!.points)
                    _destinationLocations.value =
                        Pair(listOf(selectTrip.origin), listOf(selectTrip.destination))
                    _markerSelect.value = true
                } catch (e: Exception) {
                    Log.i("titi", e.message.toString())
                }
            }
//        }
    }

    private fun getRandomLocation() {
        viewModelScope.launch(dispatcher) {
            if (getRandomLocationFromDB() != null) {
                setNearbyLocations(getRandomLocationFromDB()!!.lat, getRandomLocationFromDB()!!.lng)
            } else {
                setNearbyLocations(-34.67113975510375, -58.56181551536259)
            }
        }
    }

    fun getFlowLocationFromDB() {
        viewModelScope.launch(dispatcher) {
            _markerSelect.value = false
            getGetTripsUseCase()
                .map { item ->
                    Pair(
                        item.map { it.getOriginWithTripId() },
                        item.map { it.getDestinationWithTripId() }
                    )
                }
                .collect {
                    _destinationLocations.value = it
                }
        }
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

    private fun decoPoints(points: String) {
        _polyLinesPoints.value = decodePoly(points)
    }

    /**
     * Method to decode polyline points
     * Courtesy : https://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }

        return poly
    }
}
