package com.example.turistaapp.map.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.domain.GetLastLocationUseCase
import com.example.turistaapp.map.domain.GetRouteModel
import com.example.turistaapp.map.domain.models.RouteModel
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getGetTripsUseCase: GetTripsUseCase,
    private val getRouteModel: GetRouteModel,
    private val getLastLocationUseCase: GetLastLocationUseCase
) : ViewModel() {

    private val _destinationLocations =
        MutableStateFlow<Pair<List<LocationModel>, List<LocationModel>>?>(null)
    val destinationLocations = _destinationLocations.asStateFlow()

    private val _polyLinesPoints = MutableStateFlow<List<LatLng>>(emptyList())
    val polyLinesPoints = _polyLinesPoints.asStateFlow()

    private val _markerSelect = MutableStateFlow(false)
    val markerSelect = _markerSelect.asStateFlow()

    private val _lastLocation = MutableStateFlow<LatLng?>(null)
    val lastLocation = _lastLocation.asStateFlow()

    private val _tripSelected = MutableStateFlow<RouteModel?>(null)
    val tripSelected = _tripSelected.asStateFlow()

    init {
        getFlowLocationFromDB()
        getLastLocation()
    }

    private fun getLastLocation(){
        viewModelScope.launch(dispatcher) {
            if(getLastLocationUseCase() != null) {
                _lastLocation.value = LatLng(
                    getLastLocationUseCase()!!.latitude,
                    getLastLocationUseCase()!!.longitude
                )
            }
        }
    }

    fun getTripById(id: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                val selectTrip = getGetTripsUseCase().first().first { it.tripId == id }
                val selectRoute = getRouteModel(
                    origin = selectTrip.getLatLngOrigin(),
                    destination = selectTrip.getLatLngDestination(),
                    transport = selectTrip.transport,
                    trip = selectTrip
                )
                _tripSelected.value = selectRoute
                decoPoints(selectRoute!!.points)
                _destinationLocations.value =
                    Pair(listOf(selectTrip.origin), listOf(selectTrip.destination))
                _markerSelect.value = true
            } catch (e: Exception) {
                Log.i("titi", e.message.toString())
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
