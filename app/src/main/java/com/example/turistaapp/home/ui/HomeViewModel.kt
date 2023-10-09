package com.example.turistaapp.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.ResponseUiState
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

    private val _nearbyLocationsApi = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    private val _nearbyLocationSelect = MutableStateFlow<NearbyLocation?>(null)
    val nearbyLocationSelect = _nearbyLocationSelect.asStateFlow()

    init {
        setNearbyLocations(LocationApi(-34.67113975510375, -58.56181551536259))
    }

    fun setNearbyLocations(locationApi: LocationApi) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _nearbyLocationsApi.value =
                    ResponseUiState.Success(getNearbyLocationsUseCase(getLocationString(locationApi)))
            }catch (e : Exception) {
                _nearbyLocationsApi.value = ResponseUiState.Error(e.message.toString())
            }
        }

    }

    private fun getNearbyLocationByName(name: String): NearbyLocation? {
        val nearbyLocation =
            (nearbyLocations.value as ResponseUiState.Success<List<NearbyLocation>>).values
        return nearbyLocation.find { it.name == name }
    }

    fun setNearbyLocationSelect(nearbyLocationName: String) {
        val getNearbyLocation = getNearbyLocationByName(nearbyLocationName)
        _nearbyLocationSelect.value = getNearbyLocation
    }
}