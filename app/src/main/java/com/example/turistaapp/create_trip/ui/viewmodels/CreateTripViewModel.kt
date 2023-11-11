package com.example.turistaapp.create_trip.ui.viewmodels // ktlint-disable package-name

import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.core.utils.Transports
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
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val insertTripUseCase: InsertTripUseCase,
    private val getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase,
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val getNameFromDataStore: GetNameFromDataStore,
) : ViewModel() {

    /*
    // Viajes que se muestran en la lazy list
    private var _trips = MutableLiveData<List<TripModel>>()
    val trips: LiveData<List<TripModel>> = _trips
     */

    // Fechas del viaje
    val calendar: Calendar = Calendar.getInstance()

    private var _startDate = MutableLiveData(calendar.timeInMillis)
    val startDate: LiveData<Long> = _startDate
    fun onStartDateChange(startDate: Long) {
        _startDate.value = startDate
    }

    private var _endDate = MutableLiveData(calendar.timeInMillis)
    val endDate: LiveData<Long> = _endDate
    fun onEndDateChange(endDate: Long) {
        _endDate.value = endDate
    }

    private var _showDateRangePickerDialog = MutableLiveData(false)
    val showDateRangePickerDialog: LiveData<Boolean> = _showDateRangePickerDialog
    fun onShowDateRangePickerDialogChange(showDateRangePickerDialog: Boolean) {
        _showDateRangePickerDialog.value = showDateRangePickerDialog
    }

    // Miembros
    private var _members = MutableLiveData(mutableListOf<String>())
    val members: LiveData<MutableList<String>> = _members

    private var _memberName = MutableLiveData("")
    val memberName: LiveData<String> = _memberName

    fun onMemberNameChange(memberName: String) {
        _memberName.value = memberName
    }

    fun onAddMember(member: String) {
        if (!member.isBlank()) {
            val updatedMembers = _members.value?.toMutableList() ?: mutableListOf()
            updatedMembers.add(member)
            _members.value = updatedMembers
            resetMemberNameValue()
        }
    }

    fun onRemoveMember(index: Int) {
        val updatedMembers = _members.value?.toMutableList()
        updatedMembers?.removeAt(index)
        _members.value = updatedMembers
    }

    fun resetMemberNameValue() {
        _memberName.value = ""
    }
    /*
        // Paradas
        private var _stops = MutableLiveData(mutableListOf<String>())
        val stops: LiveData<MutableList<String>> = _stops

        private var _stopName = MutableLiveData("")
        val stopName: LiveData<String> = _stopName

        fun onStopNameChange(stopName: String) {
            _stopName.value = stopName
        }

        fun onAddStop(stop: String) {
            val updatedStops = _stops.value?.toMutableList() ?: mutableListOf()
            updatedStops.add(stop)
            _stops.value = updatedStops
            resetStopNameValue()
        }

        fun onRemoveStop(index: Int) {
            val updatedStops = _stops.value?.toMutableList()
            updatedStops?.removeAt(index)
            _stops.value = updatedStops
        }

        fun resetStopNameValue() {
            _stopName.value = ""
        }
     */

    // Transportes
    private var _transports = MutableLiveData(
        listOf(
            Transports.Driving.type,
            Transports.Walking.type,
            Transports.Bicycling.type,
        ),
    )
    val transports: LiveData<List<String>> = _transports

    private var _isExpanded = MutableLiveData(false)
    val isExpanded: LiveData<Boolean> = _isExpanded
    fun onIsExpandedChange(isExpanded: Boolean) {
        _isExpanded.value = isExpanded
    }

    private var _transport = MutableLiveData(Transports.Driving.type)
    val transport: LiveData<String> = _transport
    fun onTransportChange(transport: String) {
        _transport.value = transport
    }

    // Focus Requesters
    private var _originFocusRequester = MutableLiveData(FocusRequester())
    val originFocusRequester: LiveData<FocusRequester> = _originFocusRequester

    private var _destinationFocusRequester = MutableLiveData(FocusRequester())
    val destinationFocusRequester: LiveData<FocusRequester> = _destinationFocusRequester

    private var _descriptionFocusRequester = MutableLiveData(FocusRequester())
    val descriptionFocusRequester: LiveData<FocusRequester> = _descriptionFocusRequester

    // Ubicaciones
    // Lista de predicciones del Origen
    private val _originPredictions =
        MutableLiveData<List<PlaceAutocompletePredictionModel>>(emptyList())
    val originPredictions: LiveData<List<PlaceAutocompletePredictionModel>> get() = _originPredictions

    // Busca predicciones de ubicaciones para el Origen según una query
    fun searchOriginPlaces(query: String) {
        viewModelScope.launch {
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query)
            _originPredictions.value = newPredictions
        }
    }

    // Origen seleccionado en las predicciones
    private val _selectedOriginLocation = MutableLiveData<PlaceAutocompletePredictionModel?>(null)
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
            val newPredictions = getPlaceAutocompleteLocationsUseCase.invoke(query)
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

    private var _isDestinationAutocompleteDropdownVisible = MutableLiveData(true)
    val isDestinationAutocompleteDropdownVisible: LiveData<Boolean> =
        _isDestinationAutocompleteDropdownVisible

    fun onDestinationAutocompleteDropdownVisibilityChange(isVisible: Boolean) {
        _isDestinationAutocompleteDropdownVisible.value = isVisible
    }

    private val _selectedDestinationLocation =
        MutableLiveData<PlaceAutocompletePredictionModel?>(null)

    fun onSelectedDestinationLocationChange(selectedLocation: PlaceAutocompletePredictionModel) {
        _selectedDestinationLocation.value = selectedLocation
    }

    fun validateTripName(tripName: String): Boolean {
        if (tripName.isBlank()) {
            return false
        }
        return true
    }

    fun validateTripOrigin(): Boolean {
        if (_selectedOriginLocation.value == null) {
            return false
        }
        return true
    }

    fun validateTripDestination(): Boolean {
        if (_selectedDestinationLocation.value == null) {
            return false
        }
        return true
    }

    // Crear viaje con los datos ingresados

    fun onCreateTripClick(name: String, description: String) {
        viewModelScope.launch {
            val origin = getPlaceDetailsUseCase(_selectedOriginLocation.value!!.placeId)
            val destination =
                getPlaceDetailsUseCase(_selectedDestinationLocation.value!!.placeId)

            val trip = TripModel(
                name = name,
                origin = origin!!,
                destination = destination!!,
                startDate = startDate.value.toString(),
                endDate = endDate.value.toString(),
                transport = _transport.value.toString(),
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
