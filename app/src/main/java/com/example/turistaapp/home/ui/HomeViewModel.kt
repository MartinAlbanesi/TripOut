package com.example.turistaapp.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.getLocationString
import com.example.turistaapp.home.data.api.model.LocationApi
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.models.NearbyLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNearbyLocationsUseCase: GetNearbyLocationsUseCase
) : ViewModel() {

    private val _nearbyLocationsApi = MutableStateFlow<List<NearbyLocation>?>(null)
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    private val _nearbyLocationSelect = MutableStateFlow<NearbyLocation?>(null)
    val nearbyLocationSelect = _nearbyLocationSelect.asStateFlow()

    init {
        setNearbyLocations(LocationApi(-34.67113975510375, -58.56181551536259))
    }
    fun setNearbyLocations(locationApi : LocationApi) {
        viewModelScope.launch(Dispatchers.IO) {
            _nearbyLocationsApi.value = getNearbyLocationsUseCase(getLocationString(locationApi))
        }
    }

    private fun getNearbyLocationByName(name: String) : NearbyLocation? {
        return if(nearbyLocations.value != null) nearbyLocations.value!!.find { it.name == name } else null
    }

    fun setNearbyLocationSelect(nearbyLocationName: String) {
        val getNearbyLocation = getNearbyLocationByName(nearbyLocationName)
        _nearbyLocationSelect.value = getNearbyLocation
    }
}