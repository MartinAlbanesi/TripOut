package com.example.turistaapp.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.core.utils.getLocationString
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import com.example.turistaapp.home.domain.models.NearbyLocation
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
    private val getRandomLocationFromDB: GetRandomLocationFromDB
) : ViewModel() {

    private val _nearbyLocationsApi = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val nearbyLocations = _nearbyLocationsApi.asStateFlow()

    private val _nearbyLocationSelect = MutableStateFlow<NearbyLocation?>(null)
    val nearbyLocationSelect = _nearbyLocationSelect.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            if(getRandomLocationFromDB() != null){
                setNearbyLocations(getRandomLocationFromDB()!!.lat, getRandomLocationFromDB()!!.lng)
//                Log.i("titi", getRandomLocationFromDB()!!.lat.toString())
//                Log.i("titi", getRandomLocationFromDB()!!.lng.toString())
            }else{
                setNearbyLocations(-34.67113975510375, -58.56181551536259)
//                Log.i("titi", "ta mal")
            }
        }
    }

    fun setNearbyLocations(lat : Double, lng : Double) {

        viewModelScope.launch(dispatcher) {
            try {
                val nearbyLocations = getNearbyLocationsUseCase(getLocationString(lat,lng))

//                Log.i("titi", nearbyLocations.toString())

                if(nearbyLocations.isNullOrEmpty()){
                    _nearbyLocationsApi.emit(ResponseUiState.Error("No se encontraron lugares cercanos"))
                }else{
                    _nearbyLocationsApi.emit(ResponseUiState.Success(nearbyLocations))
                }
            }catch (e : Exception) {
                _nearbyLocationsApi.emit(ResponseUiState.Error(e.message.toString()))
            }
        }

    }

    fun setNearbyLocationSelect(nearbyLocationName: String) {
        val getNearbyLocation = getNearbyLocationByName(nearbyLocationName)
        _nearbyLocationSelect.value = getNearbyLocation
    }

    private fun getNearbyLocationByName(name: String): NearbyLocation? {
        val nearbyLocation =
            (nearbyLocations.value as ResponseUiState.Success<List<NearbyLocation>>).values
        return nearbyLocation.find { it.name == name }
    }
}
