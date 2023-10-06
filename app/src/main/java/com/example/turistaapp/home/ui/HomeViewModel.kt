package com.example.turistaapp.home.ui

import android.util.Log
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

    private val _nearbyLocationsApi = MutableStateFlow<List<NearbyLocation>>(emptyList())
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    init {
        getNearbyLocations(LocationApi(-34.67113975510375, -58.56181551536259))
    }
    fun getNearbyLocations(locationApi : LocationApi) {
        viewModelScope.launch(Dispatchers.IO) {
            _nearbyLocationsApi.value = getNearbyLocationsUseCase(getLocationString(locationApi))

            _nearbyLocationsApi.value.forEach {
                Log.i("titi", it.toString())
            }
        }
    }
}