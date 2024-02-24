package com.example.turistaapp.create_trip.ui.viewmodels // ktlint-disable package-name

import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import com.example.turistaapp.create_trip.domain.GetPlaceDetailsUseCase
import com.example.turistaapp.create_trip.domain.InsertTripUseCase
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.qr_code.domain.models.DataQRModel
import com.example.turistaapp.qr_code.domain.models.toTripModel
import com.example.turistaapp.welcome.domain.GetNameFromDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val insertTripUseCase: InsertTripUseCase,
    private val getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase,
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val getNameFromDataStore: GetNameFromDataStore,
) : ViewModel() {

    // Miembros
    private var _members = MutableLiveData(mutableListOf<String>())
    val members: LiveData<MutableList<String>> = _members

    fun onAddMember(member: String) {
        if (member.isNotBlank()) {
            val updatedMembers = _members.value?.toMutableList() ?: mutableListOf()
            updatedMembers.add(member)
            _members.value = updatedMembers
//            resetMemberNameValue()

        }
    }

    fun onRemoveMember(index: Int) {
        val updatedMembers = _members.value?.toMutableList()
        updatedMembers?.removeAt(index)
        _members.value = updatedMembers
    }

    // Ubicaciones
    // Lista de predicciones del Origen
    private val _originPredictions =
        MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val originPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _originPredictions

    // Busca predicciones de ubicaciones para el Origen según una query
    fun searchOriginPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query, "geocode")
            _originPredictions.value = newPredictions
        }
    }

    // Origen seleccionado en las predicciones
    private var _selectedOriginLocation = MutableLiveData<PlaceAutocompletePredictionModel?>(null)
    fun clearSelectedOriginLocation() {
        _selectedOriginLocation.value = null
    }

    fun onSelectedOriginLocationChange(selectedLocation: PlaceAutocompletePredictionModel) {
        _selectedOriginLocation.value = selectedLocation
    }

    // Lista de predicciones del Destino
    private val _destinationPredictions =
        MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val destinationPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _destinationPredictions

    // Busca predicciones de ubicaciones para el Destino según una query
    fun searchDestinationPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query, "establishment")
            _destinationPredictions.value = newPredictions
        }
    }

    // Setea
    fun setDestination(address: String?) {
        searchDestinationPlaces(address!!)
    }

    fun clearSelectedDestinationLocation() {
        _selectedDestinationLocation.value = null
    }

    private val _selectedDestinationLocation =
        MutableLiveData<PlaceAutocompletePredictionModel?>(null)

    fun onSelectedDestinationLocationChange(selectedLocation: PlaceAutocompletePredictionModel) {
        _selectedDestinationLocation.value = selectedLocation
    }

    fun validateTripName(tripName: String): Boolean {
        return !(tripName.isBlank() || tripName.length < 3 || tripName.length > 30)
    }

    fun validateTripOrigin(): Boolean {
        return _selectedOriginLocation.value != null
    }

    fun validateTripDestination(): Boolean {
        return _selectedDestinationLocation.value != null
    }

    // Crear viaje con los datos ingresados

    fun onCreateTripClick(
        name: String,
        description: String,
        transport: String,
        startDate: String,
        endDate: String,
    ) {
        viewModelScope.launch {
            val origin = getPlaceDetailsUseCase(_selectedOriginLocation.value!!.placeId)
            val destination =
                getPlaceDetailsUseCase(_selectedDestinationLocation.value!!.placeId)

            val trip = TripModel(
                name = name,
                origin = origin!!,
                destination = destination!!,
                startDate = startDate,
                endDate = endDate,
                transport = transport,
                members = _members.value,
                stops = null,
                description = description,
                author = getNameFromDataStore().first()!!,
                images = null,
                comments = null,
                isFavorite = false,
                isFinished = false,
            )
            insertTripUseCase.execute(trip)
        }
    }

    fun createTripFromQR(dataQR: DataQRModel) {
        viewModelScope.launch {
            val trip = dataQR.toTripModel(getPlaceDetailsUseCase)
            insertTripUseCase.execute(trip)
        }
    }
}
